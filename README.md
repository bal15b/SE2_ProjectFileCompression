# SE2ProjectFileCompression
 This program is able to compress and uncompress files using both LZW and Huffman algorithms as well as combine several files into a single compressed archive. SchubsL.java is used compress using LZW, SchubsH.java is used compress using Huffman, SchubsArc.java is used to archive and compress several files, and Deschubs is use to decompress all three types of files produced.

# Design 
 Both LZW and Huffman have pros and cons to using them. LZW's biggest pro is that is can compress repeated information incredibly well but if the file has little to no repetition in it, the compressed file can end up being larger than the uncompressed one. Huffman is much more consistent on how it compresses the file by reducing the amount of data required to store a character, but it does not handle repetition as well as LZW.

# How to run tests
1. Download repository and navigate to it on the terminal
2. Run the command “mvn test”

#How to run SchubsL, SchubsH, and Deschubs
1. Download repository and navigate to it on the terminal
2. cd in src, then main, then java
3. To compile type javac < SchubsL, SchubsH, or Deschubs >
4. Place files you want to compress or uncompress in the folder
5. To run type java < SchubsL, SchubsH, or Deschubs> <list of files separated by spaces>
(SchubsL compresses with LZW)
(SchubsH compresses with Huffman)
(Deschubs uncompresses .ll .hh and .zl files)

#How to run SchubsArc
1. Download repository and navigate to it on the terminal
2. cd in src, then main, then java
3. To compile type javac < SchubsArc>
4. Place files you want to archive in the folder
5. To run type java SchubsArc <name of archive file.zl> > <list of text files separated by spaces>
(SchubsArc will create a .zl archive)



