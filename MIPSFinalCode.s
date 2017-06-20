.data
 _a:	.word 0
 _b:	.word 0
.text
.globl main
main:
li $t0, 1
sw $t1, _b
add $t2, $t0, $t1
li $t0, 1
sub $t1, $t2, $t0
sw $t1, _a
li $v0, 10
syscall
