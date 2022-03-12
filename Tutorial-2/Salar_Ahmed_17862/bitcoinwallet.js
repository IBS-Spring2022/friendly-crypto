const crypto = require("crypto");
const { RIPEMD160 } = require("crypto-js");
var sha256 = require("js-sha256");
const ripemd160 = require("ripemd160-js");
var sha512 = require("js-sha512");
const NodeRSA = require("node-rsa");
const { binary_to_base58 } = require("base58-js");
const message = "Salar Ahmed";

const { publicKey, privateKey } = crypto.generateKeyPairSync("rsa", {
  modulusLength: 2048,
  publicKeyEncoding: {
    type: "spki",
    format: "pem",
  },
  privateKeyEncoding: {
    type: "pkcs8",
    format: "pem",
  },
});

console.log(publicKey);
console.log(privateKey);

const hash1 = sha256(message);
console.log("Hashed with SHA 256:", hash1);

const hash2 = ripemd160(hash1).then((a) =>
  console.log("Hashed with RIPEMD:", a)
); //Promise response returned in a

const hash3 = sha512(message);
console.log("Hashed with SHA 512:", hash3);

const key = new NodeRSA({ b: 2048 });

const keyData =
  "-----BEGIN PUBLIC KEY-----  MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsvYXzjsFIk4tTZ484RIp\n" +
  "XkY2rnUNrEYTCea2s3QSgSAfCaxLvQNNwZHnOGY+0etElMf2+olG+YIxTvWEILB+\n" +
  "Zp/IFsaB8pFxy61MElS8uZcwwObmT4AhwedFDjEqMVdPDM/wnaxAekxPRplB6uZ/\n" +
  "/bfHp+b6Wi2utGkAHaojotXbqxY7/6DwI5GYdPqvRl+OVQ5Uymdm/ZU1wAA6p56h\n" +
  "SEvapzd9AR1Prv9LIz822FzYvDVxJeH3iHfkx9KmYdtaRKYMAIeg5W1WHsvJKDfg\n" +
  "Y5gV0VXDK1ERM7qT4Kwe0ivcDj7PY5cds4kZJgZfBPy9kGBJPrE0KSYb2uT4DImn\n" +
  "aQIDAQAB\n  -----END PUBLIC KEY-----"; //using shaheerahmed's public key

key.importKey(keyData, "public");
const encryptedtext = key.encrypt(message, "base64");
console.log("Encrypted Name: ", encryptedtext); //can be decrypted with shaheer ahmed's private key

key.importKey(privateKey, "pkcs8");
const signedtext = key.encrypt(message, "base64"); //signed name by encrypting with private key
console.log("Signed Name:", signedtext);

key.importKey(publicKey, "public");
const decrypted = key.decrypt(signedtext, "utf8"); //decrypted to verify the signature
console.log("Decryption of signed text with public key: ", decrypted);

//bitcoin wallet generation
const sha_hash = sha256(publicKey);
//console.log(sha_hash.toString());

const ripemd_hash = RIPEMD160(sha_hash);
//console.log(ripemd_hash.toString());

const ripemdWithVersionByte = "00" + ripemd_hash.toString();
//console.log(ripemdWithVersionByte);

const SHA_2 = sha256(sha256(ripemdWithVersionByte.toString()).toString());
//console.log(SHA_2);

const checkSum = SHA_2.substring(0, 8);
//console.log(checkSum);

const wallet_address = ripemdWithVersionByte + checkSum;
//console.log(wallet_address);

const wallet_address_base_58 = binary_to_base58(wallet_address);
console.log("Bitcoin Address:", wallet_address_base_58);
