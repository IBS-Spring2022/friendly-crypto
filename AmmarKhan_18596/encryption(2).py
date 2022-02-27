#!/usr/bin/env python
# coding: utf-8

# In[1]:


import hashlib;

x = hashlib.new('ripemd160')

x.update(b"Ammar")

print("This is Hashed and Hex Encoded by RipeMD-160: ")
print(x.hexdigest())


# In[6]:


#pip install bitcoin


# ## from bitcoin import *
# 
# private_key = random_key()
# print(private_key)
# 
# public_key = privtopub(private_key)
# print(public_key)
# 
# address = pubtoaddr(public_key)
# print("My address is: " + address) 
