import binascii
import hashlib
import rsa
import base58 as base58
import ecdsa as ecdsa


#geenrating bitcoin wallet address method
def wallet_address():
    ecdsaPrivateKey = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
    ecdsaPublicKey = '04' + ecdsaPrivateKey.get_verifying_key().to_string().hex()
    hash256FromECDSAPublicKey = hashlib.sha256(binascii.unhexlify(ecdsaPublicKey)).hexdigest()
    ridemp160FromHash256 = hashlib.new('ripemd160', binascii.unhexlify(hash256FromECDSAPublicKey))
    prependNetworkByte = '00' + ridemp160FromHash256.hexdigest()
    #double hash of sha-256
    hash = hashlib.sha256(hashlib.sha256(str(prependNetworkByte).encode('ascii')).digest()).hexdigest()
    checksum = hash[:8]
    
    appendChecksum = prependNetworkByte + checksum
    #convert to base58
    bitcoinAddress = base58.b58encode(binascii.unhexlify(appendChecksum))
    print("Bitcoin Address: ", bitcoinAddress.decode('utf8'))


def ripemd160(name):
    hash = hashlib.new('ripemd160')
    hash.update(name)
    hash.digest()
    print("RipeMD160: ",hash.hexdigest())


if __name__ == "__main__":
    name = b"Muhammad Osama"
    ripemd160(name)
    wallet_address()