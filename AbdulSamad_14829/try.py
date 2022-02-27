
from inspect import signature
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.Hash import SHA256
import hashlib
import binascii
import base64
import base58




keyPair = RSA.generate(2048)
 
pubKey = keyPair.publickey()
##print(f"Public key:  (n={hex(pubKey.n)}, e={hex(pubKey.e)})")
pubKeyPEM = pubKey.exportKey("PEM")
print(pubKeyPEM.decode('ascii'))
 
##print(f"Private key: (n={hex(pubKey.n)}, d={hex(keyPair.d)})")
privKeyPEM = keyPair.exportKey("PEM")
print(privKeyPEM.decode('ascii'))

pubkeybase64 = base64.b64encode(pubKeyPEM.decode('ascii'))
##pvtkeybase64 = base64.b64encode(privKeyPEM.decode('ascii'))

#Hashing name

x=hashlib.new('SHA256')
x.update(b"Abdul Samad Qureshi")
print ("SHA-256:" ,x.hexdigest())

x=hashlib.new('ripemd160')
x.update(b"Abdul Samad Qureshi")
print ("ripemd-160:" ,x.hexdigest())

x=hashlib.new('SHA512')
x.update(b"Abdul Samad Qureshi")
print ("SHA-512:" ,x.hexdigest())

 
#encryption
name = 'Abdul Samad Qureshi'
Sir = 'MIIBCgKCAQEAyq4JMh49jp+NHeR3HEk/EU9wxT+sTzTimbEDW4SpBXKizdZgshGvoPS1K1pFB7hBS5CIG6narAVoTJaGGCUHR4jUm3pWpJqwWckwRPu47Ee6ZfRQAdWUpziBbM5fiqiEJVkTsqGNbSEBNFiqPVX3/rhe8eym5JIcwD8AydETJsHv2kio5Q0mohbmkEQwVguMYMYBOnyWA2D51sSFV7tJWSxME6zQZzvYOTgZYMUO+15hq62gpmkSevwdVoHC/7i4HPwdTAGiUh+AX6D70Zv9kqN/PuGn5e+Vst9VyK3So0Fv4UQeRJJ+pONOpKDTd7F4PJO+gSMxy6nana6OF5sI0QIDAQAB'
#sir public key added
encryptor = PKCS1_OAEP.new(Sir)
encrypted = encryptor.encrypt(b'Abdul Samad Qureshi')
print("Encrypted:", binascii.hexlify(encrypted))

#Signing
nameSHA = SHA256.new(b'Abdul Samad Qureshi')
signature = PKCS115_SigScheme(privKeyPEM).sign(nameSHA)
print('\nSignature',signature.hex())



def wallet_address(publickey):
       
    sha256hash = hashlib.sha256(str(publickey).encode('ascii')).hexdigest()
    ripemd160 = hashlib.new('ripemd160' , str(sha256hash).encode('ascii')).hexdigest()
    version = '00'
    ripemd160withVersionByte = version + ripemd160
    checksum = hashlib.sha256(hashlib.sha256(str(ripemd160withVersionByte).encode('ascii')).digest()).hexdigest()[:7]
    finAddress = ripemd160withVersionByte + checksum
    walletaddressnumber = '1' + base58.b58encode(finAddress.encode('ascii')).decode('ascii')
    return walletaddressnumber

print(wallet_address(pubkeybase64))