.data
 _a:	.word 0
 _b:	.word 0
 _c:	.space 40
 _e:	.word 0
 _msg1:	.asciiz "meow"
 _msg2:	.asciiz "write"
 _msg3:	.asciiz "watever"
 _msg4:	.asciiz "value of a: "
 _msg5:	.asciiz "value of a: "
 _msg6:	.asciiz "value of a: "
 _msg7:	.asciiz "das"
.text
.globl main
main:
li $t0, 0
li $t1, 1
beq $t0, $t1, L1
b L2
L1:
li $t0, 1
li $t1, 1
add $t2, $t0, $t1
sw $t2, _a
li $v0, 4
la $a0, _msg1
syscall
b L3
L2:
li $t0, 2
li $t1, 1
add $t3, $t0, $t1
li $t0, 1
bgt $t3, $t0, L4
b L5
L4:
li $t0, 3
li $t1, 4
blt $t0, $t1, L6
b L5
L6:
li $v0, 4
la $a0, _msg2
syscall
b L3
L5:
li $v0, 4
la $a0, _msg3
syscall
b L3
L3:
li $t0, 10
sw $t0, _a
L9:
lw $t1, _a
li $t3, 20
ble $t1, $t3, L7
b L8
L7:
li $v0, 4
la $a0, _msg4
syscall
li $v0, 1
lw $a0, _a
syscall
lw $t1, _a
li $t3, 1
add $t4, $t1, $t3
sw $t4, _a
b L9
L8:
li $t1, 11
sw $t1, _a
L11:
li $v0, 4
la $a0, _msg5
syscall
li $v0, 1
lw $a0, _a
syscall
lw $t3, _a
li $t5, 1
add $t6, $t3, $t5
sw $t6, _a
lw $t3, _a
li $t5, 20
beq $t3, $t5, L10
b L11
L10:
li $t3, 12
sw $t3, _a
L14:
lw $t5, _a
li $t7, 20
blt $t5, $t7, L12
b L13
L12:
li $v0, 4
la $a0, _msg6
syscall
li $v0, 1
lw $a0, _a
syscall
lw $t5, _a
li $t7, 1
add $t8, $t5, $t7
sw $t8, _a
b L14
L13:
li $v0, 4
la $a0, _msg7
syscall
li $v0, 10
syscall
