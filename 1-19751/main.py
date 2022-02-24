from inspect import signature
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
import binascii,hashlib
import base64
import base58
def generate_wallet_address(publickey):
        sha256hash = hashlib.sha256(str(publickey).encode('ascii')).hexdigest()
        ripemd160 = hashlib.new('ripemd160', str(sha256hash).encode('ascii')).hexdigest()
        version = '00'
        ripemd160withversionByte = version+ripemd160
        checksum = hashlib.sha256(hashlib.sha256(str(ripemd160withversionByte).encode('ascii')).digest()).hexdigest()[:7]
        
        final_address = ripemd160withversionByte+checksum
        wallet_address = '1' + base58.b58encode(final_address.encode('ascii')).decode('ascii')
        return wallet_address
       

if __name__== "__main__":
    myName = b"Abdul Moiz Choudry"
    #keyPair = RSA.generate(2048)
    #publickey = keyPair.publickey()
   # with open('mypvtkey.pem','wb') as f:
   #     f.write(keyPair.exportKey('PEM'))
    #    f.close()
   # with open('muPubkey.pem', 'wb') as f:
   #     f.write(publickey.exportKey('PEM'))
   #     f.close()

    pvtKey= RSA.importKey(open('myPvtKey.pem').read())
    pubKey= RSA.importKey(open('myPubKey.pem').read())


    sirPubKey = RSA.import_key(open('SirsPubKey.pem').read())
    sirPvtKey = RSA.import_key(open('SirsPvtKey.pem').read())

    
    sirpvtKey= RSA.importKey(open('SirsPvtKey.pem').read())
    sirpubKey= RSA.importKey(open('SirsPubKey.pem').read())


    print(pvtKey,pubKey)

my_pvt_key_base64 = base64.b64encode(pvtKey.exportKey('PEM')).decode()
my_pub_key_base64 = base64.b64encode(pubKey.exportKey('PEM')).decode('ascii')



print("public Key:\t",my_pub_key_base64)
print("\nprivate Key:",my_pub_key_base64)

sha256hash = hashlib.sha256(str(myName).encode('ascii')).hexdigest()
sha512hash = hashlib.sha512(str(myName).encode('ascii')).hexdigest()
ripemd160hash = hashlib.new('ripemd160',str(myName).encode('ascii')).hexdigest()

print("SHA-256:  ",sha256hash)
print("SHA-512:  ",sha512hash)
print("RIPEMD-160:  ",ripemd160hash)



encryptor = PKCS1_OAEP.new(sirPubKey)
encrypted = encryptor.encrypt(myName)
print("\nEncrypted:", binascii.hexlify(encrypted))

signature = PKCS115_SigScheme(pvtKey).sign(SHA256.new(myName))
print('\nSignature: ', signature.hex())


print("Wallet address: ",generate_wallet_address(my_pub_key_base64))