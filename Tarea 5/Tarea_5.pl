% Lo que hace sepparimpar es dividir la lista L en una cabeza y una
% cola con sublista de cola, de forma que para los elementos en
% posiciones pares agrega el elemento en pos 0 a P y para los casos
% impares agrega el elemento en la pos 1 a I, luego iterando el resto de
% la lista de forma recursiva.

% Caso base.
sepparimpar([], [], []).
%Caso en que el tamaño de la lista es par.
sepparimpar([X,Y|T], [X|Z], [Y|W]) :- sepparimpar(T, Z, W).
%Caso en que el tamaño de la lista es impar.
sepparimpar([X|Y],[X|Z],I) :-sepparimpar(Y,Z,I).


%Caso base.
todosrango(_, Max, Max).
% Comparacion recursiva para identificar que en L esten presente todos
% los numeros del intervalo[Minimo,Maximo)
todosrango(L, Minimo, Maximo) :-
    member(Minimo, L),
    succ(Minimo, Sucesor),
    todosrango(L, Sucesor, Maximo).

%Caso Base.
rangomax(L,Min,Max):- Max-Min = Max-1.

%Se revisa que este todos los numeros del intervalo en la lista
rangomax(L,Min,Max):-
    member(Min,L),
    succ(Min,Next),
    rangomax(L,Next,Max).


%Verifica que el Min y el Max sean ambos par o impar.
verificar(Min,Max):-
    (0 is mod(Min,2), 0 is mod(2,2));(1 is mod(Min,2), 1 is mod(Max,2)).

% Se generan 2 listas con los intervalos pedidos, desde los cuales se
% aplica la funcion sepparimpar para encontrar la lista requerida

%Caso en que Min y Max son ambos par o impar.
chicograndechico(L,Min,Max):-
    verificar(Min,Max),
    N is Max+Min,
    N1 is N//2,
    lista(Min,N1,L1),
    lista(N1,Max,L2),
    sepparimpar(L,L1,L2).

%Caso en que Min es par o impar y Max es el contrario de lo que sea Min.
chicograndechico(L,Min,Max):-
    N is Max+Min,
    N1 is N+1,
    N2 is N-1,
    N3 is N1//2,
    N4 is N2//2,
    lista(Min,N3,L1),
    lista(N4,Max,L2),
    sepparimpar(L,L1,L2).


%Lista que se crea desde 0 hasta N.
lista(I,F,[]):-I=F,!.
lista(I,F,[I|R]):- I1 is I+1, lista(I1, F,R).

