program hola;
var 
    a: integer;
    b: Array[1..10] of integer;
    c: boolean;
begin
    if NOT( a > 1) AND (1 > a) then
    begin
        a := 1;
    end
    else if ( a > 2 ) then
    begin 
        a := 2;
    end
    else
    begin
        a := 3;
    end;

end.
