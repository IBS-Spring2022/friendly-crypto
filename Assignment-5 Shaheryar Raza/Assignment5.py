import hashlib
x = hashlib.new('ripemd160')
x.update(b"Shaheryar Raza")
print("The hexadecimal equivalent of hash is :")
print(x.hexdigest())


#Encrypting sha 256 key through ripemd
x.update(b"40798DA4F927787A5C9671CA4BD0DCDA5B18258F5ECCB5246DA8F89AF0A98")
print("The hexadecimal equivalent of hash is :")
print(x.hexdigest())