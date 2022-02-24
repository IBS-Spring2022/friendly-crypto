from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Hash import SHA256
import binascii
import hashlib
import base64
import base58


def generate_wallet_address(publickey):

    sha256hash = hashlib.sha256(str(publickey).encode('ascii')).hexdigest()
    ripemd160 = hashlib.new('ripemd160', str(
        sha256hash).encode('ascii')).hexdigest()
    version = '00'
    ripemd160withVersionByte = version + ripemd160
    checksum = hashlib.sha256(hashlib.sha256(
        str(ripemd160withVersionByte).encode('ascii')).digest()).hexdigest()[:7]
    final_address = ripemd160withVersionByte + checksum
    wallet_address = '1' + \
        base58.b58encode(final_address.encode('ascii')).decode('ascii')
    return wallet_address

    print("Final Address", final_address)


if __name__ == "__main__":
    keypair = RSA.generate(2048)
    publicKey = keypair.publickey()

    with open('mypvtkey.pem', 'wb') as f:
        f.write(keypair.exportKey('PEM'))
    f.close()
    with open('mypubkey.pem', 'wb') as f:
        f.write(publicKey.exportKey('PEM'))
        f.close()

    pvtKey = RSA.import_key(open('mypvtkey.pem').read())
    pubKey = RSA.import_key(open('mypubkey.pem').read())

    # sir pub and pvt key

    sirPubKey = RSA.import_key(open('SirsPubKey.pem').read())
    sirPvtKey = RSA.import_key(open('SirsPvtKey.pem').read())

    print(pubKey, pvtKey)

my_pubKey_base64 = base64.b64encode(pubKey.exportKey('PEM')).decode('ascii')
my_pvtKey_base64 = base64.b64encode(pvtKey.exportKey('PEM')).decode()
print("Public key:\t", my_pubKey_base64)
print("\nPrivate key", my_pvtKey_base64)

myName = b"Ahmed Ghafoor"

    # hash
sha256hash = hashlib.sha256(str(myName).encode('ascii')).hexdigest()
sha512hash = hashlib.sha512(str(myName).encode('ascii')).hexdigest()
ripemd160 = hashlib.new('ripemd160', str(myName).encode('ascii')).hexdigest()

print("SHA-256: ", sha256hash)
print("SHA-512: ", sha512hash)
print("SHA-160: ", ripemd160)

encryptor = PKCS1_OAEP.new(sirPubKey)
encrypted = encryptor.encrypt(myName)
print("\nEncrypted:", binascii.hexlify(encrypted))

signature = PKCS115_SigScheme(pvtKey).sign(SHA256.new(myName))
print('\nSignature', signature.hex())
print("Wallet_Address", generate_wallet_address(my_pubKey_base64))