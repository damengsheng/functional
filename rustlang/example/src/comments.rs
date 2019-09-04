//! 文档注释
//!
//! 描述包含这个注释的项,一般用在模块文件头部
//!
//! 两种文档注释在分发的时候可以装换成 `HTML`
//!
//! 文档注释支持 `Markdown`
//! [reference: nightly comments](https://doc.rust-lang.org/nightly/reference/comments.html)

/*!
    块文档注释
*/

//!  - Inner line doc
//!! - Still an inner line doc (but with a bang at the beginning)

/*!  - Inner block doc */
/*!! - Still an inner block doc (but with a bang at the beginning) */

// empty inner block doc
/*!*/

//   - Only a comment
///  - Outer line doc (exactly 3 slashes)
//// - Only a comment

/*   - Only a comment */
/**  - Outer block doc (exactly) 2 asterisks */
/*** - Only a comment */

// empty line comment
//

// empty outer line doc
///

// empty block comment
/**/

// empty 2-asterisk block isn't a doc block, it is a block comment
/***/

/// 文档注释
///
/// 用来描述跟随注释的项
/** 块文档注释 */
pub fn comments() {
    /* In Rust /* we can /* nest comments */ */ */

    // All three types of block comments can contain or be nested inside
    // any other type:

    /*   /* */  /** */  /*! */  */
    /*!  /* */  /** */  /*! */  */
    /**  /* */  /** */  /*! */  */
    println!("四种注释...")
}
// 普通注释： 行注释
// 🐽

/*
    普通注释: 块注释
*/
