.data
 _c:    .space 30
.text
.globl main
main:
li $v0, 8
la $a0, _c
li $a1, 30
syscall
li $v0, 4
la $a0, _c
syscall
li $v0, 10
syscall
