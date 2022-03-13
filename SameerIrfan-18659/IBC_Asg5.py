from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.backends import default_backend
from bitcoin import *
import hashlib
import binascii
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import padding



# generate private/public key pair
key = rsa.generate_private_key(backend=default_backend(), public_exponent=65537, \
    key_size=2048)

# get public key in OpenSSH format
public_key = key.public_key().public_bytes(serialization.Encoding.OpenSSH, \
    serialization.PublicFormat.OpenSSH)

# get private key in PEM container format
pem = key.private_bytes(encoding=serialization.Encoding.PEM,
    format=serialization.PrivateFormat.TraditionalOpenSSL,
    encryption_algorithm=serialization.NoEncryption())

# decode to printable strings
private_key_str = pem.decode('utf-8')
public_key_str = public_key.decode('utf-8')

print()
print('Private key = ') 
print(private_key_str)
print('Public key = s')
print(public_key_str)
print("\n")
inp = 'Sameer Irfan Kazi'

hash256 = hashlib.sha256(inp.encode('utf-8')).hexdigest()
print("SHA 256")
print(hash256)
print("\n")

ripemd160 = hashlib.new('ripemd160')
ripemd160.update(b'Sameer Irfan')
print("RIPE MD")
print(ripemd160.hexdigest())
print(" \n")

hash512 = hashlib.sha512(inp.encode('utf-8')).hexdigest()
print("SHA 512")
print(hash512)
print("\n")


address = pubtoaddr(public_key)
print("My Adress is: " + address )
print()
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.asymmetric import rsa
private_key = rsa.generate_private_key(
        public_exponent=65537,
        key_size=2048,
        backend=default_backend()
    )
public_key = private_key.public_key()

# Storing the keys
from cryptography.hazmat.primitives import serialization
pem = private_key.private_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PrivateFormat.PKCS8,
        encryption_algorithm=serialization.NoEncryption()
    )
with open('private_key.pem', 'wb') as f:
    f.write(pem)

pem = public_key.public_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PublicFormat.SubjectPublicKeyInfo
    )
with open('public_key.pem', 'wb') as f:
    f.write(pem)

# Reading the keys back in (for demonstration purposes)
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import serialization
with open("private_key.pem", "rb") as key_file:
        private_key = serialization.load_pem_private_key(
            key_file.read(),
            password=None,
            backend=default_backend()
        )
with open("public_key.pem", "rb") as key_file:
        public_key = serialization.load_pem_public_key(
            key_file.read(),
            backend=default_backend()
        )

# Encrypting and decrypting
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import padding

message = b'Sameer Irfan'
encrypted = public_key.encrypt(
        message,
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )
original_message = private_key.decrypt(
        encrypted,
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )

print("Text to be encrypted: " )
print(original_message)
print()
print("Encrypted Text: " )
print(encrypted)
