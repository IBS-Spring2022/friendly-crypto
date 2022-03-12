import rsa
import base58
import base64
import hashlib, binascii

def generate_keys(pvt_filename, pub_filename):
    (pubKey, pvtKey) = rsa.newkeys(2048)
    with open(pub_filename, 'wb') as publicfile:
        publicfile.write(pubKey.save_pkcs1('PEM'))

    with open(pvt_filename, 'wb') as publicfile:
        publicfile.write(pvtKey.save_pkcs1('PEM'))

def load_keys(pvt_filename, pub_filename):
    with open(pub_filename, 'rb') as publicfile:
        pubKey = rsa.PublicKey.load_pkcs1(publicfile.read())
        # print(pubKey)
    with open(pvt_filename, 'rb') as privatefile:
        pvtKey = rsa.PrivateKey.load_pkcs1(privatefile.read())
        # print((pvtKey))
    return (pubKey, pvtKey)

def encrypt_message(message, pubKey):
    return rsa.encrypt(message, pubKey)

def decrypt_message(message, pvtKey):
    try:
        return rsa.decrypt(message, pvtKey).decode('ascii')
    except:
        return False

def sign_sha256(message, pvtKey):
    hash = rsa.compute_hash(message, 'SHA-256')
    return rsa.sign_hash(hash, pvtKey, 'SHA-256')

def verify_sha256(message, signature, pubKey):
    try:
        return rsa.verify(message.encode('ascii'), signature, pubKey) == 'SHA-256'
    except:
        return False



def generate_sha_256_hash(message):
    """
    Generate SHA-256 hash of the message.
    """
    return hashlib.sha256(str(message).encode('ascii')).hexdigest()
    

def generate_ripemd_160_hash(message):
    """
    Generate RIPEMD-160 hash of the message.
    """
    return hashlib.new('ripemd160', str(message).encode('ascii')).hexdigest()

def generate_sha_512_hash(message):
    """
    Generate SHA-512 hash of the message.
    """
    return hashlib.sha512(str(message).encode('ascii')).hexdigest()

def generate_wallet_address_from_public_key_P2PKH(public_key, network):
    """
    Generate wallet address from public key.
    """
    # print("public key:", public_key)
    sha_256_hash = generate_sha_256_hash(public_key)
    print("sha256 Hash:",sha_256_hash)
    # return
    ripemd_160_hash = generate_ripemd_160_hash(sha_256_hash)
    print("RIPEMD:160",ripemd_160_hash)
    if network == "main":
        ripemd_160_hash_with_network_prefix = "00" + ripemd_160_hash
    elif network == "test":
        ripemd_160_hash_with_network_prefix = "01" + ripemd_160_hash

    print("RIPEMD with version byte: ",ripemd_160_hash_with_network_prefix)
    checksum  = generate_sha_256_hash(generate_sha_256_hash(ripemd_160_hash_with_network_prefix))[:8]
    print("checksum",checksum)
    binary_bitcoin_address = ""+ripemd_160_hash_with_network_prefix + checksum
    print("binary_bitcoin_address",binary_bitcoin_address)
    wallet_address = base58.b58encode(binary_bitcoin_address.encode('ascii'))

    return "1"+wallet_address.decode('ascii')

def read_pem(data):
    """Read PEM formatted input."""
    data = data.replace(b"\n",b"")
    data = data.replace(b"",b"")
    data = data.replace(b"-----BEGIN PUBLIC KEY-----",b"")
    data = data.replace(b"-----END PUBLIC KEY-----",b"")
    return binascii.hexlify(base64.b64decode(data))

if __name__ == '__main__':
    my_key_private_filename = 'keys/my_private.pem'
    my_key_public_filename = 'keys/my_public.pem'

    other_key_private_filename = 'keys/other_private.pem'
    other_key_public_filename = 'keys/other_public.pem'
    
    # uncomment below code to generate news keys
    # generate_keys(my_key_private_filename,my_key_public_filename) # My Keys
    # generate_keys(other_key_private_filename,other_key_public_filename) # Other Keys
    
    (my_pub_key, my_pvt_key) = load_keys(my_key_private_filename,my_key_public_filename) # Loading My Keys
    (other_pub_key, other_pvt_key) = load_keys(other_key_private_filename,other_key_public_filename) # Loading Other Keys


    # print((bytes(str(my_pub_key).encode('ascii'))))
    # print(my_pub_key)
    # print(type(my_pvt_key))
    # convert my pubkey to base64
    # my_pub_key_base64 = read_pem(my_pub_key.save_pkcs1('PEM'))

    my_pvt_key_base64 = base64.b64encode(my_pvt_key.save_pkcs1('PEM')).decode('ascii')
    my_pub_key_base64 = base64.b64encode(my_pub_key.save_pkcs1('PEM')).decode('ascii')

    print("Base64 pub key:\t",my_pub_key_base64)
    print("\n\nBase64 pvy key:\t",my_pvt_key_base64)
    
    # print("Public Key:",hex(my_pub_key.n))
    # print("Private Key:", hex(my_pvt_key.n))
    message = 'Nabeel Ahmed'.encode('ascii')

    # Hashing your name with mutiple algorithms
    sha_256_hash = generate_sha_256_hash(message)
    sha_512_hash = generate_sha_512_hash(message)
    ripemd_160_hash = generate_ripemd_160_hash(message)

    print("\n\nSHA-256 Hash:",sha_256_hash)
    print("\n\nSHA-512 Hash:",sha_512_hash)
    print("\n\nRIPEMD-160 Hash:",ripemd_160_hash)
    
    cipher_text = encrypt_message(message, other_pub_key) # Encrypting Message with other public key
    print('\nEncrypted Message', cipher_text.hex())
    
    signature = sign_sha256(message, my_pvt_key) # Signing Message with my private key
    print('\nSignature', signature.hex())

    decrypted = decrypt_message(cipher_text, other_pvt_key)
    print('\nDecrypted Message', decrypted)

    # print(base64.b64decode(my_pub_key_base64))
    bitcoin_address = generate_wallet_address_from_public_key_P2PKH(base64.b64decode(my_pub_key_base64), "main")
    print("Final wallet Address",bitcoin_address)
    # if ve
