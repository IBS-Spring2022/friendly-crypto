from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.Hash import SHA256
import hashlib, binascii
from base64 import b64encode
from base58 import b58encode

def generateKeys():
    # Generate Keys and save them to file
    keyPair = RSA.generate(2048)
    pubKeyPEM = keyPair.publickey().exportKey()
    privKeyPem = keyPair.exportKey()
    # print(privKeyPem.decode('ascii'))
    # print(pubKeyPEM.decode('ascii'))

    f = open('pubKey.pem','wb')
    f.write(keyPair.publickey().export_key('PEM'))
    f.close()
    f2 = open('privKey.pem','wb')
    f2.write(keyPair.export_key('PEM'))
    f2.close()

def loadEverything():
    # Read keys from file
    f3 = open('privKey.pem','r')
    privKey = RSA.import_key(f3.read())
    f4 = open('pubKey.pem','r')
    pubKey = RSA.import_key(f4.read())
    f5 = open('privKeyOther.pem','r')
    privKeyOther = RSA.import_key(f5.read())
    f6 = open('pubKeyOther.pem','r')
    pubKeyOther = RSA.import_key(f6.read())

    # print("privKeyBase64:", b64encode(privKey.exportKey('PEM')).decode())
    # print("\npubKeyBase64:", b64encode(pubKey.export_key('PEM')).decode())
    # print("\nprivKeyOtherBase64:", b64encode(privKeyOther.exportKey('PEM')).decode())
    # print("\npubKeyOtherBase64:", b64encode(pubKeyOther.export_key('PEM')).decode(), "\n")

    return (privKey, pubKey, privKeyOther, pubKeyOther)

def printHashes():
    message = "Ammar"
    data = message.encode('utf-8')
    
    sha256hash = hashlib.sha256(data).hexdigest()
    print("\nSHA-256 hash:", sha256hash, "\n")
    sha512hash = hashlib.sha512(data).hexdigest()
    print("SHA-512 hash:", sha512hash, "\n")
    ripemd160hash = hashlib.new('ripemd160', data).hexdigest()
    print("RIPEMD-160 hash:", ripemd160hash, "\n")


def printEncryptAndDecrypt(privKeyOther, pubKeyOther):
    message = b'Ammar'
    encryptor = PKCS1_OAEP.new(pubKeyOther)
    encrypted = encryptor.encrypt(message)
    print("Encrypted:", binascii.hexlify(encrypted), "\n")

    decryptor = PKCS1_OAEP.new(privKeyOther)
    decrypted = decryptor.decrypt(encrypted)
    print('Decrypted:', decrypted, "\n")

def signAndVerify(privKey, pubKey):
    # Signing my name
    signature = PKCS115_SigScheme(privKey).sign(SHA256.new(message))
    print("Signature:", binascii.hexlify(signature), "\n")

    # Verifying my signature
    try:
        PKCS115_SigScheme(pubKey).verify(SHA256.new(message), signature)
        print("Signature is valid\n")
    except (ValueError, TypeError):
        print("Signature is invalid")


def getWalletAddress(pubKey): 
    sha256hash = hashlib.sha256(str(pubKey).encode('ascii')).hexdigest()

    ripemd160hash = hashlib.new('ripemd160', str(sha256hash).encode('ascii')).hexdigest()
    
    # Assuming mainnet
    versionPrefixed = "00" + ripemd160hash
    
    checksum = hashlib.sha256(hashlib.sha256(str(versionPrefixed).encode('ascii')).digest()).hexdigest()[:8]
    
    binaryAddress = versionPrefixed + checksum
    
    base58Address = b58encode(binaryAddress)
    
    return "1" + base58Address.decode('ascii')


if __name__ == '__main__':
    # If you want to generate new keys
    # generateKeys()

    # Loading previously generated keys from files
    (privKey, pubKey, privKeyOther, pubKeyOther) = loadEverything()
    
    # Hashing my name with SHA-256, SHA-512, RIPEMD-160
    printHashes()

    # Encrypting and decrypting my name
    message = b'Ammar'
    printEncryptAndDecrypt(privKeyOther, pubKeyOther)

    signAndVerify(privKey, pubKey)

    # Getting my bitcoin wallet address
    print("Bitcoin Wallet Address", getWalletAddress(b64encode(pubKey.export_key('PEM')).decode('ascii')))