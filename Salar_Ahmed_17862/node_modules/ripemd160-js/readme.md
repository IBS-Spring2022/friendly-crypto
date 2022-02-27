![RIPEMD1600 logo](https://raw.githubusercontent.com/pur3miish/ripemd160-js/main/static/ripemd-160.svg)

# ripemd160-js

[![NPM Package](https://img.shields.io/npm/v/ripemd160-js.svg)](https://www.npmjs.org/package/ripemd160-js) [![CI status](https://github.com/pur3miish/ripemd160-js/workflows/CI/badge.svg)](https://github.com/pur3miish/ripemd160-js/actions) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/pur3miish/ripemd160-js/blob/main/LICENSE)

A [Universal JavaScript](https://en.wikipedia.org/wiki/Isomorphic_JavaScript) [RIPEMD160](https://en.bitcoin.it/wiki/RIPEMD-160) cryptographic hash function.

**Features**

- Zero dependencies.
- Adds 3.4 kB to bundle.

# Setup

```shell
$ npm i ripemd160-js
```

# Support

- [Node.js](https://nodejs.org/en/) `>= 12`
- [Browser list](https://github.com/browserslist/browserslist) `defaults` `not IE 11`

# API

## function ripemd160

Performs the message digest algorithm RIPEMD160 on input data.

| Parameter | Type                 | Description   |
| :-------- | :------------------- | :------------ |
| `message` | string \| Uint8Array | Data to hash. |

**Returns:** string | Uint8Array — Preserves the data return type.

### Examples

_Ways to `import`._

> ```js
> import ripemd160 from 'ripemd160-js'
> ```

_Ways to `require`._

> ```js
> const ripemd160 = require('ripemd160-js')
> ```

_Usage `Sting` `ripemd160`._

> ```js
> ripemd160('hello').then(console.log)
> ```
>
> The logged output will be “108f07b8382412612c048d07d13f814118445acd”.

_Usage `Uint8Array` `ripemd160`._

> ```js
> const print_hex = array => Buffer.from(array).toString('hex')
> ripemd160(new Uint8Array([1, 2, 3])).then(print_hex)
> ```
>
> The logged output will be “79f901da2609f020adadbf2e5f68a16c8c3f7d57”
