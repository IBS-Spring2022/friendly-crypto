const { SHA256, SHA512, RIPEMD160 } = require("crypto-js");
const crypto = require("crypto");
const NodeRSA = require("node-rsa");
const { binary_to_base58 } = require("base58-js");

const name = "Abdullah";

// generating hashes
const nameHash256 = SHA256(name);
const nameRIPEMD = RIPEMD160(name);
const nameHash512 = SHA512(name);
console.log(`SHA-256: ${nameHash256.toString()}`);
console.log(`RIPEMD: ${nameRIPEMD.toString()}`);
console.log(`SHA-512: ${nameHash512.toString()}\n\n\n`);

//generating keys
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

// Encrypting and signing data
const key = new NodeRSA({ b: 2048 });
const abdulMoizKey =
  "-----BEGIN PUBLIC KEY-----  MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxLK9+W0jhTWrbzltZo4gmLvybNmrDNvP0qWk5iysjXCeVxDrmnFuoFrJHXW32feYF2wBl6T4cngkZuRVlCPqCnMMGZwvTxaIGM/HGsgma45+tDHSI0qec4BVDFVyPC11ndace5OS3iP6XctOgjT00JdSz6SBfIVw9A0IpCLt7POKnIVrSl4mtiDwrypEomy1gG8b4HwlLKpS6WRXECQD/Yt/PSii7K2tlKB2tibAOUg1EMbBIhxIm5yGB9SiJO5KmHsQDArLMT6sbIIppEpluhVlLgoWFa/PkiI0pX0y7M3e6T35Zw0vDX46D33ntdRAlsyCz7qLzDdEmqsAfT0EcQIDAQAB  -----END PUBLIC KEY-----";
key.importKey(abdulMoizKey, "public");
const encryptedtext = key.encrypt(name, "base64");
console.log(`Encrypted Name:  ${encryptedtext}\n\n`);

key.importKey(privateKey, "pkcs8");
const signedtext = key.encrypt(name, "base64");
console.log(`Signature: ${signedtext}\n\n`);
// Uncomment the following part to see if the decrypt value is same as 'Abdullah'
// key.importKey(publicKey, "public");
// const decrypted = key.decrypt(signedtext, "utf8");
// console.log("decrypted: ", decrypted);

//wallet Address creation
const hashResult = SHA256(publicKey);
// console.log(hashResult.toString());
const ripemd = RIPEMD160(hashResult);
// console.log(ripemd.toString());
const ripemdWithVersionByte = "00" + ripemd.toString();
// console.log(ripemdWithVersionByte);
const doubleSHA = SHA256(SHA256(ripemdWithVersionByte).toString()).toString();
// console.log(doubleSHA);
const checkSum = doubleSHA.substring(0, 8);
// console.log(checkSum);
const binaryBitcoinAddress = ripemdWithVersionByte + checkSum;
// console.log(binaryBitcoinAddress);
const bitcoinAddress = binary_to_base58(binaryBitcoinAddress);
console.log(`Bitcoin Address = ${bitcoinAddress}`);
