from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
import base64
import base58


import hashlib
import binascii


def generate_public_private_keys():
    key = RSA.generate(2048)
    p = key.publickey().exportKey('PEM')
    with open('mypublickey.pem', 'w') as pf:
        pf.write(p.decode())
        pf.close()
    with open('myprivatekey.pem', 'w') as pf:
        pf.write(key.exportKey('PEM').decode())
        pf.close()


def wallet_adress(public_key):
    sha256 = hashlib.sha256(str(public_key).encode('ascii')).hexdigest()

    ripemd160 = hashlib.new('ripemd160', str(
        sha256).encode('ascii')).hexdigest()
    # print(ripemd160)

    version = '00'
    hashWithVersion = version + ripemd160
    checksum = hashlib.sha256(hashlib.sha256(
        hashWithVersion.encode('ascii')).digest()).hexdigest()[:7]
    print("checksum:", checksum)

    address = hashWithVersion + checksum

    wallet_adress = '1' + \
        base58.b58encode(address.encode('ascii')).decode('ascii')

    return wallet_adress


if __name__ == '__main__':
    # Run once
    # generate_public_private_keys('mykey.pem')
    print('Keys generated')

    pvtKey = RSA.importKey(open('myprivatekey.pem').read())
    pubKey = RSA.importKey(open('mypublickey.pem').read())

    # Import sirs public and Private key
    sirPubKey = RSA.import_key(open('sir_public.pem').read())
    sirPvtKey = RSA.import_key(open('sir_private.pem').read())

    my_pvt_key_base64 = base64.b64encode(pvtKey.exportKey('PEM')).decode()
    my_pub_key_base64 = base64.b64encode(
        pubKey.export_key('PEM')).decode('ascii')

    print("Public key:\t", my_pvt_key_base64)
    print("\nPrivate Key", my_pub_key_base64)

    myName = b"Samima Khan"

    # Hash the name
    sha256hash = hashlib.sha256(str(myName).encode('ascii')).hexdigest()
    sha512hash = hashlib.sha512(str(myName).encode('ascii')).hexdigest()
    ripemd160 = hashlib.new('ripemd160', str(
        myName).encode('ascii')).hexdigest()

    print("SHA-256:   ", sha256hash)
    print("SHA-512:   ", sha512hash)
    print("RIPEMD-160:   ", ripemd160)

    encryptor = PKCS1_OAEP.new(sirPubKey)
    encrypted = encryptor.encrypt(myName)
    print("\nEncrypted:", binascii.hexlify(encrypted))

    signature = PKCS115_SigScheme(pvtKey).sign(SHA256.new(myName))
    print('\nSignature', signature.hex())

    # walllet address
    bitcoinAddress = wallet_adress(my_pub_key_base64)
    print("Address", bitcoinAddress)
    # wallet_address = hashlib.sha256(str(my_pub_key_base64).encode('ascii')).hexdigest()
    # print(wallet_address)
    # decryptedText = PKCS1_OAEP.new(sirPvtKey).decrypt(encrypted)
    # print("Decrypted Text", decryptedText)
    # encryptedText = pubKey.encrypt(str(myName).encode('ascii'), 32)
