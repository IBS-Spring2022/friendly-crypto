




use rsa::pkcs1::{ToRsaPublicKey, ToRsaPrivateKey};
use rust_base58::{ToBase58};
use crypto::digest::{Digest};
use crypto::sha2::{Sha256,Sha512};
use crypto::ripemd160::Ripemd160; 
use rsa::{PublicKey, RsaPrivateKey, RsaPublicKey, PaddingScheme};
use rand::rngs::OsRng;
use std::path::Path;








fn main() {


   let mut rng = OsRng;
let bits = 2048;
let private_key = RsaPrivateKey::new(&mut rng, bits).expect("failed to generate a key");
let public_key = RsaPublicKey::from(&private_key);

let name = b"Nabeel";
let name_sha256 = sha256(name);
let name_ripemd160 = ripemd160(name);
let name_sha512 = sha512(name);
let path = Path::new("PRIVATE_TXT.txt");
let path2 = Path::new("PUBLIC_TEXT.txt");

let encrypted = public_key.encrypt(&mut rng, PaddingScheme::new_pkcs1v15_encrypt(), &name_sha256).unwrap();
let dec_data = private_key.decrypt(PaddingScheme::new_pkcs1v15_encrypt(), &encrypted
).expect("failed to decrypt");

private_key.write_pkcs1_pem_file(path);
public_key.write_pkcs1_pem_file(path2);
let public_serialized =public_key.to_pkcs1_pem().unwrap();
println!("publc key is {}",public_serialized);

println!("encrypted data is {}",encrypted.to_base58());
println!("decrypted data is {}",dec_data.to_base58());
assert_eq!(&name_sha256,&dec_data[..]);
let mut version_byte = vec![0x00];
let bitcoin_address_part1 = sha256(public_key.to_pkcs1_pem().unwrap().as_bytes());
let mut bitcoin_address_part2 = ripemd160(&bitcoin_address_part1);
let bitcoin_address_part3 = version_byte.extend(bitcoin_address_part2.iter());
let checksum = double_sha256(&version_byte);
version_byte.extend(checksum[..4].iter());
let signature = private_key.sign(PaddingScheme::new_pkcs1v15_sign(None), &name_sha256).unwrap();
println!("bitcoin wallet address is  {}",version_byte.to_base58());
println!("Signature is {}",signature.to_base58())




// let name_sha512 = sha512(name);
 
 



  
   //using ecdsa
//     let mut num = OsRng::new().unwrap();
//     let secp = Secp256k1::new();
//     let (sk,pk) = secp.generate_keypair(&mut num);
//     let keypair = KeyPair::from_secret_key(&secp,sk);
//    let secret = keypair.display_secret();
//    let name = "Nabeel";
//    let nameHex = name.as_bytes();
//    let nameSha256 = sha256::Hash::hash(nameHex);

//    let nameRipeMd = ripemd160::Hash::hash(nameHex);
//    let nameSha512 = sha512::Hash::hash(nameHex);
//    let msgToSign = Message::from_slice(&nameSha256).unwrap();
//    let sig = secp.sign_ecdsa(&msgToSign,&sk);
//    let verify = secp.verify_ecdsa(&msgToSign, &sig, &pk).is_ok();
//    let serializedPK = pk.serialize_uncompressed();
//    let firstPKHash = sha256::Hash::hash(&serializedPK);
//    let secondRipeMdHash = ripemd160::Hash::hash(&firstPKHash);
//    let mut addVersionByte = "00".to_string();
//    addVersionByte.push_str(&secondRipeMdHash.to_string());
//    println!("version byte after {}",addVersionByte);
//    let twiceSha256 = sha256::Hash::hash(&sha256::Hash::hash(addVersionByte.as_bytes()));
//    let firstFour = addVersionByte.as_bytes().to_vec().extend(twiceSha256[..4].iter().clone());
//    println!("version byte after vec {}",addVersionByte);

//    println!("version byte after vec  b58{}",addVersionByte.as_bytes().to_base58());


// //    let firstSha = sha256.(pk);
// //step 1 sha256 pb
   
//    println!("here is sk {}, \n here is pb {}",secret,pk);
//    println!("sha256 {}, \n ripeMD {}, \n sha512 {}",nameSha256,nameRipeMd,nameSha512);
//    println!("here is my signature {}",sig);
//    println!("signing message {} \n , verifying {}",msgToSign,verify);
  

    
}
// fn generate_bitcoin_address(bytes : &[u8]) -> Vec<u8> {

// }

fn double_sha256(bytes : &[u8]) -> Vec<u8> {
   let mut hasher = Sha256::new();
   let mut hash = vec![0; hasher.output_bytes()];
   hasher.input(&bytes);
   hasher.result(&mut hash);
   hasher.reset();
   hasher.input(&hash);
   hasher.result(&mut hash);
   return hash;
}

fn hash160(bytes : &[u8]) -> Vec<u8> {
   let mut res = sha256(&bytes);
   res = ripemd160(&res);
   return res;
}

//return sha256 as a byte array
fn sha256(bytes : &[u8]) -> Vec<u8> {
   let mut hasher = Sha256::new();
   let mut hash = vec![0; hasher.output_bytes()];
   hasher.input(&bytes); 
   hasher.result(&mut hash);
   return hash;
}
fn sha512(bytes : &[u8]) -> Vec<u8> {
   let mut hasher = Sha512::new();
   let mut hash = vec![0; hasher.output_bytes()];
   hasher.input(&bytes); 
   hasher.result(&mut hash);
   return hash;
}

//return ripemd as a byte array
fn ripemd160(bytes : &[u8]) -> Vec<u8> {
   let mut ripemder = Ripemd160::new();
   let mut hash = vec![0; ripemder.output_bytes()];
   ripemder.input(&bytes); 
   ripemder.result(&mut hash);
   return hash;
}

