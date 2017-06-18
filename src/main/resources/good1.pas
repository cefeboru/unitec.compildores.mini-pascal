program hola;
var 
    a,b,c,d:integer;
    e: boolean;
function x2(var z:boolean;x:integer;var y:char):boolean;
var 
    a:integer;
begin
    a := 1;
    if ( a = 2) then
    begin
        e := true;
    end;
    x2 := true;
end;
begin
    a := 1;
    write('Hola');
    e := x2( true , 1 * 2, 'a');
end.


