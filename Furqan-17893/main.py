import binascii
import base64
import base58
from Crypto.PublicKey import RSA
from Crypto.Hash import SHA256,SHA512,RIPEMD160
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import pkcs1_15


# key = RSA.generate(2048)
# private_key = key.export_key('PEM')
# f = open("myprivate.pem", "wb")
# f.write(private_key)
# f.close()

# public_key = key.publickey().export_key('PEM')
# file_out = open("mypublic.pem", "wb")
# file_out.write(public_key)
# file_out.close()

hash_object = SHA256.new(data=b'Furqan Saeed')
print("SHA-256 \n\t" + hash_object.hexdigest())

h = RIPEMD160.new(data=bytes(hash_object.hexdigest(),'ascii'))
print("\nRIPEMD-160 \n\t" + h.hexdigest())

hash_object2 = SHA512.new(data=bytes(h.hexdigest(),'ascii'))
print("\nSHA-512 \n\t" + hash_object2.hexdigest())

mypublic = RSA.import_key(open("mypublic.pem","r").read())
myprivate = RSA.import_key(open("myprivate.pem","r").read())
sirpublic = RSA.import_key(open("sirpublic.pem","r").read())
sirprivate = RSA.import_key(open("sirprivate.pem","r").read())

print("\n",mypublic, myprivate)
print("\n",sirpublic,sirprivate)


mypublicbase64 = base64.b64encode(mypublic.export_key('PEM')).decode('ascii')
myprivatebase64 = base64.b64encode(myprivate.export_key('PEM')).decode('ascii')

print("\nPublic key in base64 \n\t" +  mypublicbase64)
print("\nPrivate key in base64 \n\t" +  myprivatebase64)

e = PKCS1_OAEP.new(sirpublic)
encrypted = e.encrypt(b"Furqan Saeed")
print("\n")
print(binascii.hexlify(encrypted))

signature = pkcs1_15.new(myprivate).sign(SHA256.new(b"Furqan Saeed"))
print("\n")
print(signature.hex())


def generateWalletAddress(publicKey):
    print("\n\n\n")
    # SHA256 Hash
    hash_public_sha256 = SHA256.new(bytes(str(publicKey).encode('ascii')))
    print(hash_public_sha256.hexdigest())

    # RIPEMD Hash
    hash_public_ripemd160 = RIPEMD160.new(bytes(hash_public_sha256.hexdigest(), 'ascii'))
    print(hash_public_ripemd160.hexdigest())

    # Version appended
    hash_public_ripemd160_main = "00"+ hash_public_ripemd160.hexdigest()
    hash_public_ripemd160_test = "01"+ hash_public_ripemd160.hexdigest()

    print(hash_public_ripemd160_main)
    print(hash_public_ripemd160_test)

    # Hashing twice with SHA-256
    hash_public_sha256_first = SHA256.new(bytes(hash_public_ripemd160_main, 'ascii'))
    hash_public_sha256_second = SHA256.new(bytes(hash_public_sha256_first.hexdigest(), 'ascii'))

    print(hash_public_sha256_second.hexdigest())

    # Get checksum
    checksum = hash_public_sha256_second.hexdigest()[0:8]
    print(checksum)

    # Wallet address
    walletaddress = base58.b58encode(bytes(hash_public_ripemd160_main + checksum, 'ascii'))

    print(walletaddress)

generateWalletAddress(mypublic)