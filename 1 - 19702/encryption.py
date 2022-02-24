import hashlib

x = hashlib.new('ripemd160')
x.update(b"Huda")
print("The hexadecimal equivalent of hash is :") 
print(x.hexdigest())

#publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7NUHRYfpIoFt2zzPzeuLxpj0V0XWZp+WCCkV+LaWteFPF/b/KnhwkoU/tfPOniwBW9h1va3pKcFsk9WZzVIGzOJ3B74ezxnvR6Sh7L+uBF4N/q5Og89/fgsiL+jPd4549N8agsh/3fweORLJy6E0bkdipA/uDYsuYzzIxFveyMlz7xG6ccCnJuRb2l0wd4pSETgWF6A1SdTbvjwMTBH56V2nLVX2WgkuiGRzwWoa6HPvjNy+3QiWnZooQR/ujWHDiq+UCv5lpL7xpVku7UrWIicXBewsyJQJCEzmPr8F3R2i+uy5JwZwPQQEb8hfYv/Zhlhxkr36uKeT+hPxOy3glQIDAQAB"
firstHash = hashlib.sha256()
firstHash.update(b"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7NUHRYfpIoFt2zzPzeuLxpj0V0XWZp+WCCkV+LaWteFPF/b/KnhwkoU/tfPOniwBW9h1va3pKcFsk9WZzVIGzOJ3B74ezxnvR6Sh7L+uBF4N/q5Og89/fgsiL+jPd4549N8agsh/3fweORLJy6E0bkdipA/uDYsuYzzIxFveyMlz7xG6ccCnJuRb2l0wd4pSETgWF6A1SdTbvjwMTBH56V2nLVX2WgkuiGRzwWoa6HPvjNy+3QiWnZooQR/ujWHDiq+UCv5lpL7xpVku7UrWIicXBewsyJQJCEzmPr8F3R2i+uy5JwZwPQQEb8hfYv/Zhlhxkr36uKeT+hPxOy3glQIDAQAB")
secondHash = hashlib.new('ripemd160')
secondHash.update(firstHash.hexdigest().encode())
wallet = hashlib.sha512()
wallet.update(secondHash.hexdigest().encode())
print(wallet.hexdigest())


