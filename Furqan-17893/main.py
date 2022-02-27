import binascii
import base58
from Crypto.PublicKey import RSA
from Crypto.Hash import SHA256,SHA512,RIPEMD160
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import pkcs1_15


def generateRSAKeys():
    key = RSA.generate(2048)
    private_key = key.export_key('PEM')
    f = open("myprivate.pem", "wb")
    f.write(private_key)
    f.close()

    public_key = key.publickey().export_key('PEM')
    file_out = open("mypublic.pem", "wb")
    file_out.write(public_key)
    file_out.close()

def decryptText(privateKey, encryptedHex):
    cipher = PKCS1_OAEP.new(privateKey)
    message = cipher.decrypt(binascii.unhexlify(encryptedHex))
    print("\n" + str(message.decode("ascii")))

def verifySignature(publicKey, signatureHex, hash):
    print()
    try:
        pkcs1_15.new(publicKey).verify(hash, binascii.unhexlify(signatureHex))
        print("VERIFIED SIGNATURE")
    except:
        print("UNVERIFIED SIGNATURE")

def generateWalletAddress(publicKey):
    print("\nGENERATING WALLET ADDRESS:\n")
    # SHA256 Hash
    hash_public_sha256 = SHA256.new(bytes(str(publicKey).encode('ascii')))
    print("\t1: SHA-256 hash of public key: " + str(hash_public_sha256.hexdigest()))

    # RIPEMD Hash
    hash_public_ripemd160 = RIPEMD160.new(bytes(hash_public_sha256.hexdigest(), 'ascii'))
    print("\t2: RIPEMD-160 hash of SHA-256 hash: " + str(hash_public_ripemd160.hexdigest()))

    # Version appended
    hash_public_ripemd160_main = "00"+ hash_public_ripemd160.hexdigest()
    hash_public_ripemd160_test = "01"+ hash_public_ripemd160.hexdigest()

    print("\t3: RIPEMD-160 hash with mainnet version byte: " + str(hash_public_ripemd160_main))
    print("\t4: RIPEMD-160 hash with testnet version byte: " + str(hash_public_ripemd160_test))

    # Hashing twice with SHA-256
    hash_public_sha256_first = SHA256.new(bytes(hash_public_ripemd160_main, 'ascii'))
    hash_public_sha256_second = SHA256.new(bytes(hash_public_sha256_first.hexdigest(), 'ascii'))

    print("\t5: SHA-256 hash twice of RIPEMD-160 hash with mainnet version byte: " + str(hash_public_sha256_second.hexdigest()))

    # Get checksum
    checksum = hash_public_sha256_second.hexdigest()[0:8]
    print("\t6: Checksum from generated hash in step 5: " + str(checksum))

    # Wallet address
    walletaddress = base58.b58encode(binascii.unhexlify(hash_public_ripemd160_main + checksum)).decode("ascii")

    print("\t7: WALLET ADDRESS: " + str(walletaddress))

mypublic = RSA.import_key(open("mypublic.pem","r").read())
myprivate = RSA.import_key(open("myprivate.pem","r").read())
sirpublic = RSA.import_key(open("sirpublic.pem","r").read())
sirprivate = RSA.import_key(open("sirprivate.pem","r").read())

print("\nMy Keys: " + str(mypublic), myprivate)
print("Sir's Keys: " + str(sirpublic), sirprivate, "\n")

hash_object = SHA256.new(data=b'Furqan Saeed')
print("SHA-256 \n\t" + hash_object.hexdigest())

h = RIPEMD160.new(data=bytes(hash_object.hexdigest(),'ascii'))
print("\nRIPEMD-160 \n\t" + h.hexdigest())

hash_object2 = SHA512.new(data=bytes(h.hexdigest(),'ascii'))
print("\nSHA-512 \n\t" + hash_object2.hexdigest())

e = PKCS1_OAEP.new(sirpublic)
encrypted = e.encrypt(b"Furqan Saeed")
print("\nEncrypted Key: ")
print("\t" + str(binascii.hexlify(encrypted).decode("ascii")))

signature = pkcs1_15.new(myprivate).sign(hash_object)
print("\nSignature: ")
print("\t" + str(signature.hex()))

generateWalletAddress(mypublic)

# verifySignature(mypublic, "75c690452ce08c764de69178ae7994913cf98d020d99d71b8791668b97eca525ad4826bb32abb8f1368ab98dd720b451fe7adcfba6116bd10806a8124816248626de15bc4abc5816ee8c692661e52eb0d0563baa6f256012022093488df9ac1c6f89538fe6e4ad9411475c3d07b09e34167206ce203d26c2f2726ffdd29357d759cea7e28a58ff3ae94132c446e6f66efac27653287bebff0a0083e162eddb59ee6a23b95a73b08d83b4976daf3b8b51cc91f10143f96487a8dda320c4ed731af0d65652f9116f72384ffcbdcb0e40cc035416320998152e8e8791c94a895839824798802854bed8d47aef3fd09ec5c3e642e2d0adaac9697483034cf8e0f388", hash_object)
# decryptText(sirprivate, "8649f0ed9b1fce5f2e508c2fad860ce021503004881c836f53cf0dab6b11e6178340cde1af33093fb3c6060da42db9c032172d0ea69ca3c06ce54de80c5078b9a1462be9d246e9a32c2b9ea685ff096004fc5f823f48259ed152f5722a0bb4b21e51028d2fe8536bbd95f62f3985a9a9c642c148735684c019c95d29a32f48dac536f392e6aece0e72a9fca31fe7902c2b5a49e789b273dd491d4bd570c8ad4e0b7f06fcc9574494ea30d3e5dfe06fdb189954e741f62b662597f50052635ec6dff7fbbb69161c0d710141064fb9f970faf4484674c36bc9194c37d99f32273b5f3dbccaf570c6521de360d81dca0e43bad86df178698c950f79b351f5643778")