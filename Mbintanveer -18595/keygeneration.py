import base64
from Crypto import Random
from Crypto.PublicKey import RSA

def toBase64(string):
    return base64.encode(string)

def generation_of_keys():
    "Sampletext"
    mod_length=256*4
    private_key=RSA.generate(mod_length,Random.new().read)
    public_key=private_key.publickey()
    return private_key, public_key

priv_key,publ_key=generation_of_keys()

private_key=priv_key.exportKey()
public_key=publ_key.exportKey()

print(private_key.decode(),"\n\n\n", public_key.decode())
