'use strict'

const { WASM } = require('../private/ripemd160.json')

/**
 * Performs the message digest algorithm RIPEMD160 on input data.
 * @kind function
 * @name ripemd160
 * @param {string | Uint8Array} message Data to hash.
 * @returns {string | Uint8Array} Preserves the data return type.
 * @example <caption>Ways to `import`.</caption>
 * ```js
 * import ripemd160 from 'ripemd160-js'
 * ```
 * @example <caption>Ways to `require`.</caption>
 * ```js
 * const ripemd160 = require('ripemd160-js')
 * ```
 * @example <caption>Usage `Sting` `ripemd160`.</caption>
 * ```js
 * ripemd160('hello').then(console.log)
 * ```
 * The logged output will be “108f07b8382412612c048d07d13f814118445acd”.
 *
 * @example <caption>Usage `Uint8Array` `ripemd160`.</caption>
 * ```js
 * const print_hex = array => Buffer.from(array).toString('hex')
 * ripemd160(new Uint8Array([1, 2, 3])).then(print_hex)
 * ```
 * The logged output will be “79f901da2609f020adadbf2e5f68a16c8c3f7d57”
 */
async function ripemd160(message) {
  if (message.length > 65536)
    throw new RangeError('Message must be less than 65,536 characters.')

  const type = typeof message

  if (typeof message == 'string')
    message = new Uint8Array(message.match(/.{1}/gmu).map(i => i.charCodeAt(0)))

  const {
    instance: {
      exports: { memory, get_ptr, digest }
    }
  } = await WebAssembly.instantiate(new Uint8Array(WASM))

  if (message instanceof Uint8Array) {
    const cArray = new Uint8Array(memory.buffer, get_ptr(1), message.length)
    cArray.set(message)
    digest(message.length)
  } else throw new TypeError('message must be of type Uint8Array or string')

  const bytes = new Uint8Array(memory.buffer).slice(get_ptr(0), get_ptr(0) + 20)

  return type == 'string'
    ? bytes.reduce((acc, i) => (acc += i.toString(16).padStart(2, '0')), '')
    : bytes
}

module.exports = ripemd160
