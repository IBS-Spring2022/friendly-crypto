#Marium Mohammad Nasir - 17876

import base64
from Crypto.PublicKey import RSA
import hashlib
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.PKCS1_v1_5 import PKCS115_SigScheme
from Crypto.Hash import SHA256
import binascii
import base58


#generating public and private key
#keyPair = RSA.generate(2048)

#privKeyPEM = keyPair.exportKey()
#with open('myprivate.pem', 'wb') as file:
    #file.write(keyPair.exportKey('PEM'))
    #file.close()

#pubKey = keyPair.publickey()
#with open('mypublickey.pem', 'wb') as file:
    #file.write(pubKey.exportKey('PEM'))
    #file.close()

pubKey = RSA.importKey(open('mypublickey.pem').read())
privKey = RSA.importKey(open('myprivate.pem').read())

baseprivKey = base64.b64encode(privKey.exportKey('PEM')).decode()
basepubKey = base64.b64encode(pubKey.exportKey('PEM')).decode()

sirpublickey = RSA.importKey(open('sirpublickey.pem').read())

sha256 = hashlib.sha256()
sha256.update(b"Marium Mohammad Nasir")
print("SHA-256 DIGEST: " + str(sha256.hexdigest()))

sha512 = hashlib.sha512()
sha512.update(b"Marium Mohammad Nasir")
print("SHA-512 DIGEST: " + str(sha512.hexdigest()))

ripemd = hashlib.new('ripemd160')
ripemd.update(b"Marium Mohammad Nasir")
print("RIPEMD160 DIGEST: " + str(ripemd.hexdigest()))

#Encrypting my name using sir public key
msg = b'Marium Mohammad Nasir'
encryptor = PKCS1_OAEP.new(sirpublickey)
encrypted = encryptor.encrypt(msg)
print("Encrypted:", binascii.hexlify(encrypted))

#digital signature
signature = PKCS115_SigScheme(privKey).sign(SHA256.new(msg))
print("Signature:", signature.hex())

#BITCOIN WALLET ADDRESS
#hash public key with SHA256
s1 = hashlib.sha256()
s1.update(str(basepubKey).encode('ascii'))

#hash digest of sha256 with ripemd160
r1 = hashlib.new('ripemd160')
r1.update(str(s1.hexdigest()).encode('ascii'))

#append 00s
ripemdWithVersion = '00' + r1.hexdigest()

#create checksum
s2 = hashlib.sha256()
byte = bytes(ripemdWithVersion, 'utf-8')
s2.update(byte)
s3 = hashlib.sha256()
s3.update(s2.digest())
checksum = s3.hexdigest()[0:4]

#generate address
address = base58.b58encode((ripemdWithVersion + checksum))
print("Wallet Address", address)

