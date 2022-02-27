
from bitcoin import *
# private_key = random_key()
# print(private_key)

public_key="""
-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWxE6R+PROV9zXxMOrUohWxWDf
IBtbr+zdxkqq81R8iLS2hLlmZ4gV8NmOevSLrUx00GCfC2F9KxCFjlxaagEMnf5T
DfRPwkxoJFgkbS1sHWpPW/nF70T9ItveLbkbVRF1JK+aC+xlkmDkpB+fjIJd8qaR
abKi5VW7vZkZN40+UQIDAQAB
-----END PUBLIC KEY-----"""

address=pubtoaddr(public_key)
print("My Address is "+address)
