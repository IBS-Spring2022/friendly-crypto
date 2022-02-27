import binascii
from Crypto.PublicKey import RSA
from Crypto.Hash import SHA256
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
import hashlib
from base64 import b64encode
from base58 import b58encode

#Key Generation
keyPair = RSA.generate(2048)
f = open('privateKey.pem', 'wb')
f.write(keyPair.export_key('PEM'))
f.close()
f = open('publicKey.pem', 'wb')
f.write(keyPair.publickey().export_key('PEM'))
f.close()

#Reading Keys from my keys and Sir's keys
f = open('privateKey.pem', 'r')
myPrivateKey = RSA.import_key(f.read())
f.close()
f2 = open('publicKey.pem', 'r')
myPublicKey = RSA.import_key(f2.read())
f2.close()
f3 = open('privateKeyOther.pem', 'r')
privateKeyOther = RSA.import_key(f3.read())
f4 = open('publicKeyOther.pem', 'r')
publicKeyOther = RSA.import_key(f4.read())

# print("My privateKey:", privateKey.decode('ascii'))
# print("My publicKey:", publicKey.decode('ascii'))
# print("Sir's privateKey:", privateKeyOther.decode('ascii'))
# print("Sir's publicKey:", publicKeyOther.decode('ascii'))
# print("\nSir's privateKey in Base64:", privateKeyOther.exportKey('PEM').decode('ascii'))
# print("\nSir's publicKey: in Base64:", publicKeyOther.export_key('PEM').decode('ascii'), "\n")

basePrivateKey=b64encode(myPrivateKey.exportKey('PEM')).decode()
basePublicKey=b64encode(myPublicKey.exportKey('PEM')).decode()


message = "Vinay".encode('ascii')

sha256_hash = hashlib.sha256(str(message).encode('ascii')).hexdigest()
print("SHA256 Hash:", sha256_hash)
sha512_hash = hashlib.sha512(str(message).encode('ascii')).hexdigest()
print("\nSHA512 Hash:", sha512_hash)
ripemd160_hash = hashlib.new('ripemd160', str(message).encode('ascii')).hexdigest()
print("\nRIPEMD160 Hash:", ripemd160_hash)

#Encryption
message = b'Vinay'
encrypted = PKCS1_OAEP.new(publicKeyOther).encrypt(message)
print("\n Encrypted: ", binascii.hexlify(encrypted))
print("\n Decrypted: ", PKCS1_OAEP.new(privateKeyOther).decrypt(encrypted), "\n")

#Signature Generation
signature= PKCS115_SigScheme(myPrivateKey).sign(SHA256.new(message))
print("Signature: ", binascii.hexlify(signature), "\n")

#Verify Signature

# try:
#     PKCS115_SigScheme(myPublicKey).verify(SHA256.new(message),signature)
#     print("Valid")
# except(ValueError, TypeError):
#     print("Invalid")

#Wallet Address Generation
sha = hashlib.sha256(str(basePublicKey).encode('ascii')).hexdigest()
ripemd = hashlib.new('ripemd160', str(sha).encode('ascii')).hexdigest()

#Version modification + Checksum
version = '00' + ripemd
checksum = hashlib.sha256(hashlib.sha256(str(version).encode('ascii')).digest()).hexdigest()[:8]

binaryAddress = version + checksum
base58Address = b58encode(binaryAddress)
walletAddress = "1" + base58Address.decode('ascii')

print("Wallet Address", walletAddress)
