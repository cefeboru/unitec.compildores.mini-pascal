.data
 _a:	.word 0
 _b:	.word 0
 _c:	.space 40
 _msg1:	.asciiz "asad"
.text
.globl main
main:
li $t0, 7
sw $t0, _a
li $t1, 3
sw $t1, _b
lw $t2, _a
lw $t3, _b
li $t4, 1
sub $t5, $t3, $t4
li $t3, 4
mult $t5, $t3
mflo $t3
la $t4, _c
add $t4, $t3, $t4
sw $t2, ($t4)
li $t2, 1
li $t3, 1
add $t5, $t2, $t3
li $t2, 1
add $t3, $t5, $t2
li $t2, 1
sub $t5, $t3, $t2
li $t2, 4
mult $t5, $t2
mflo $t2
la $t4, _c
add $t3, $t2, $t4
li $v0, 4
la $a0, _msg1
syscall
li $v0, 1
lw $a0, ($t3)
syscall
li $v0, 10
syscall
