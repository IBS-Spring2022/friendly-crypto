import base64
from Crypto import Random
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
import ast

string="My name is Muhammad Bin Tanveer, nice to meet you"
public_key_text="""-----BEGIN RSA PUBLIC KEY-----
MIIBCgKCAQEAyq4JMh49jp+NHeR3HEk/EU9wxT+sTzTimbEDW4SpBXKizdZgshGv
oPS1K1pFB7hBS5CIG6narAVoTJaGGCUHR4jUm3pWpJqwWckwRPu47Ee6ZfRQAdWU
pziBbM5fiqiEJVkTsqGNbSEBNFiqPVX3/rhe8eym5JIcwD8AydETJsHv2kio5Q0m
ohbmkEQwVguMYMYBOnyWA2D51sSFV7tJWSxME6zQZzvYOTgZYMUO+15hq62gpmkS
evwdVoHC/7i4HPwdTAGiUh+AX6D70Zv9kqN/PuGn5e+Vst9VyK3So0Fv4UQeRJJ+
pONOpKDTd7F4PJO+gSMxy6nana6OF5sI0QIDAQAB
-----END RSA PUBLIC KEY-----
"""
private_key_text="""-----BEGIN RSA PRIVATE KEY-----
MIICXAIBAAKBgQCWxE6R+PROV9zXxMOrUohWxWDfIBtbr+zdxkqq81R8iLS2hLlm
Z4gV8NmOevSLrUx00GCfC2F9KxCFjlxaagEMnf5TDfRPwkxoJFgkbS1sHWpPW/nF
70T9ItveLbkbVRF1JK+aC+xlkmDkpB+fjIJd8qaRabKi5VW7vZkZN40+UQIDAQAB
AoGACPJ47DI/Q1f0/+wKJaRl+WpIu60S2o/X6XhfTYJXwPU27j71prVdrtmme41h
7lkYOLV2CnI5NJ2l4xsl8WqpibADlPjFQouCIIfldxt8E4sxcbLnVsucB8GOCdCQ
8jlMVJSTDhSwTiEMtxEwfn5J9Zo34xF+g2yGTdXp+QXpXE0CQQC2UnrOAG6YCj/l
iTZOygy9CH478jKvHDrdjjUlDJRYhv49/AzlVvXN/FHJOPJKBISX2LiMcWG/NZst
2dE7pL2FAkEA07Ff76JOBIwbLZRdR5jvCGDWL9+046g/1oE5bM4+AZbUo3zb8Mw+
64SjUSWoKASDMqcwwvQVQmGYydCyA+FhXQJAB+Ge32LTZQ0kX/bDezpuvIkuc8bv
e/RbcxJfp79Ydk83lFRd9lgA/iuHGYy1pI93b9z1dVS9ZfeaQqwkyFBBxQJBAJZu
+enkwORiW/3r7M2wVEzz48Fpi7YsHhFlC1XSyiV+9eSoGfPocN4VB6nmxLSLYGBi
hzhDE8h0aosgDyDKzNECQAlS1lOikcbxcoHJ2DTD7g1/7yHKkLfV6yn/ghCazMWb
QcQJeIk7uk7YTiS6oCDzUAI0Ml+oEtkXiWsrOYCMxkM=
-----END RSA PRIVATE KEY-----"""

private_key = RSA.importKey(private_key_text)
public_key = RSA.importKey(public_key_text)

def encrypt():
    encryptor = PKCS1_OAEP.new(private_key)
    encrypted = encryptor.encrypt(b'Hi, this is Mbt. Nice to meet you.')
    return base64.b64encode(encrypted)

encoded_message=encrypt()

# def decrypt(encoded_message):
#     decryptor = PKCS1_OAEP.new(private_key)
#     decrypted = decryptor.decrypt(ast.literal_eval(str(encoded_message)))
#     return decrypted

print(encrypt())

# print(decrypt(encoded_message))

