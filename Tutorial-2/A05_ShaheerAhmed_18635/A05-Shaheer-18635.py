#pip install bitcoin
from bitcoin import *
import hashlib

#RipeMD160
x = hashlib.new('ripemd160')
x.update(b"Shaheer Ahmed")
print(x.hexdigest())
             
#Bitcoin Wallet Address
my_private_key = random_key()
print("Private Key: %s\n" % my_private_key)

public_key = privtopub(my_private_key)
print("Public Key: %s\n" % public_key)

wallet_address = pubtoaddr(public_key)
print("Bitcoin Wallet Address: %s\n" % wallet_address)





