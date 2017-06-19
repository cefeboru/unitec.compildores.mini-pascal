.data
 _a:	.word 0
 _b:	.word 0
.text
.globl main
main:
li $t0, 1
addi $t1, $t0, 1
li $v0, 10
syscall
