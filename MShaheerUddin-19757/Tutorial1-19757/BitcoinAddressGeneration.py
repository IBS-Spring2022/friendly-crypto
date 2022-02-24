"""
BITCOIN WALLET ADDRESS GENERATION
Using RSA Keys

@Author
Muhammad Shaheer Uddin
19757
Intro To Blockchain
Assignment 5

"""




from Crypto.PublicKey import RSA
from Crypto.Signature import pkcs1_15
from Crypto.Hash import SHA256
from Crypto.Cipher import PKCS1_OAEP
import hashlib
from ecdsa import SigningKey, SECP256k1
from cryptography.fernet import Fernet
from hashlib import sha256, sha512
from binascii import unhexlify
import base58

#Generate Private and Public Key using RSA Algorithm
keyPair = RSA.generate(bits=2048)
private_key = keyPair.export_key()
public_key = keyPair.publickey().export_key()
print(private_key)
print(public_key)

#Using Sha-256 to encrypt my name(HEX ENCODED)
m = sha256()
m.update(b"Muhammad Shaheer Uddin")
name_digest = m.digest().hex()
print("SHA256 DIGEST: "+name_digest)

#Using RIPEMD to encrypt my name (HEX ENCODED)
r = hashlib.new('rmd160')
r.update(b"Muhammad Shaheer Uddin")
print("RIPEMD DIGEST:" + r.digest().hex())

#Using SHA-512 to encrypt my name (HEX ENCODED)
r = sha512()
r.update(b"Muhammad Shaheer Uddin")
print("SHA-512 DIGEST:" + r.digest().hex())


# RSA sign the name
msg = b'Muhammad Shaheer Uddin'
hash = int.from_bytes(sha512(msg).digest(), byteorder='big')
signature = pow(hash, keyPair.d, keyPair.n)
print("Signature:", hex(signature))

#RSA encrypt the name
#Instructor's Public Key
recpubkey = """-----BEGIN PUBLIC KEY-----
MIIBCgKCAQEAyq4JMh49jp+NHeR3HEk/EU9wxT+sTzTimbEDW4SpBXKizdZgshGv
oPS1K1pFB7hBS5CIG6narAVoTJaGGCUHR4jUm3pWpJqwWckwRPu47Ee6ZfRQAdWU
pziBbM5fiqiEJVkTsqGNbSEBNFiqPVX3/rhe8eym5JIcwD8AydETJsHv2kio5Q0m
ohbmkEQwVguMYMYBOnyWA2D51sSFV7tJWSxME6zQZzvYOTgZYMUO+15hq62gpmkS
evwdVoHC/7i4HPwdTAGiUh+AX6D70Zv9kqN/PuGn5e+Vst9VyK3So0Fv4UQeRJJ+
pONOpKDTd7F4PJO+gSMxy6nana6OF5sI0QIDAQAB
-----END PUBLIC KEY-----"""
rpkey = RSA.import_key(recpubkey)
encryptor = PKCS1_OAEP.new(rpkey)
encrypted = encryptor.encrypt(b'Muhammad Shaheer Uddin')
print("Encrypted Cipher: " + encrypted.hex())

#Generating Bitcoin Address
"""
* As per my research, we can't generate bitcoin address directly from RSA Private Key
* We have to convert it to 256 bit value (ECDSA Key)
* One way is to use SHA-256 hash of RSA key and use it as ECDSA Key
"""
#Converting RSA Public Key to 256 bit
pubKey = SHA256.new(public_key)
pubKey = pubKey.digest().hex()

#Convert to RIPEMD
r = hashlib.new('rmd160')
r.update(pubKey.encode(encoding = 'UTF-8', errors = 'strict'))
riped = r.digest().hex()
print("RIPEMD DIGEST:" + riped)

#Prepend Network Bytes
prependNetworkByte = '00' + riped 

#Apply double SHA-256
hash = prependNetworkByte
for x in range(1,3):
    hash = sha256(unhexlify(hash)).hexdigest()

#Checksum - first 4 bytes
checksum = hash[:8]

#Append it with prependNetworkByte
appendChecksum = prependNetworkByte + checksum

#Generate Bitcoin Wallet Address
bitcoinAddress = base58.b58encode(unhexlify(appendChecksum))
print("Bitcoin Address: ", bitcoinAddress.decode('utf8'))

