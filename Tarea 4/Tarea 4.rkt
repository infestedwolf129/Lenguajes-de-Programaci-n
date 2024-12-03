#lang scheme (current-namespace (make-base-namespace))

;; Descripcion de la funcion makelist
;;
;; Funcion que utiliza recursion para crear una lista de N a 0.
;;
;; n: Tamaño de lista a crear desde 0 a n-1.

(define (makelist n)
  (if (= n 0)
     (list 0)                      
     (cons n (makelist (- n 1)))
     )
  )

;; Descripcion de la funcion inverso
;;
;; Funcion que recibe una lista con datos a eliminar en una posterior lista desde 0 hasta un N entregado.
;;
;; lista: Lista con datos a eliminar de la lista final.
;; n: Tamaño de lista a crear que contiene desde 0 a n-1.
(define (inverso lista n)
  (let
      ([listaCreada (sort(cdr (makelist n)) <)]);;LISTA CREADA
    (remove* lista listaCreada)
      )
    )

;; Descripcion de la funcion umbral_simple_aux
;;
;; Funcion que teniendo un lista de n numeros compara cada uno de ellos con un numero delimitante y que busca la posicion de los mayores o menores dependiendo del caracter entregado.
;;
;; lista: Lista con datos verificar que sean mayor que el umbral.
;; count: Contador que indica la posicion en la lista.
;; umbral: Numero que establece la comparacion.
;; aux_lista: Lista inicializada en vacia, que va recibiendo la posicion en la que cumple las condiciones.
;; tipo: Condicion que corresponde a buscar mayores o menores.
(define (umbral_simple_aux lista umbral tipo aux_list count)
  (cond
    [(and (equal? tipo #\M)(not(null? lista))) (if(< umbral (car lista))
                                                  (umbral_simple_aux (cdr lista) umbral tipo (append aux_list (list(+ count 0))) (+ count 1))
                                                  (umbral_simple_aux (cdr lista) umbral tipo aux_list (+ count 1)))]
    [(and (equal? tipo #\m )(not(null? lista))) (if(> umbral (car lista))
                                                   (umbral_simple_aux (cdr lista) umbral tipo (append aux_list (list(+ count 0))) (+ count 1))
                                                   (umbral_simple_aux (cdr lista) umbral tipo aux_list (+ count 1)))]
    [else aux_list]
    )
  )

;; Descripcion de la funcion umbral_simple
;;
;; Funcion que hace un llamado a una funcion auxiliar con parametros extras.
;;
;; lista: Lista con datos verificar que sean mayor que el umbral.
;; umbral: Numero que establece la comparacion.
;; tipo: Condicion que corresponde a buscar mayores o menores.
(define (umbral_simple lista umbral tipo)
  (umbral_simple_aux lista umbral tipo '() 0)
  )


;; Descripcion de la funcion verificar
;;
;; Funcion que se encarga de verificar si la posicion actual esta dentro de las buscadas.
;;
;; count: Posicion actual en la lista.
;; seleccion: Lista con las posiciones que deben ser afectadas.
(define (verificar seleccion count)
  (if (not(null? seleccion))
      (if(= (car seleccion) count)
                     #t
                     (verificar (cdr seleccion) count))
      #f)
  )

;; Descripcion de la funcion modsel_simple_aux
;;
;; Funcion auxiliar recursiva simple que se encarga de la verificacion de posiciones y afecta con una funcion lambda su indice.
;;
;; lista: Lista con datos a verificar.
;; seleccion: Lista con las posiciones que deben ser afectadas.
;; f: Funcion lambda entregada.
;; count: Contador con la posicion actual en la lista
;; list_aux: Nueva lista generada a partir de efectuar funciones lambda.
(define (modsel_simple_aux lista seleccion f count list_aux)
  (cond 
  [(not(null? lista)) (if (eq? (verificar seleccion count) #t)
          (modsel_simple_aux  (cdr lista) seleccion f (+ count 1) (append list_aux (list (f (car lista))) ))
          (modsel_simple_aux  (cdr lista) seleccion f (+ count 1) (append list_aux (list (car lista)))))]
  [else list_aux]
  ))

;; Descripcion de la funcion modsel_simple
;;
;; Funcion que llama una funcion auxiliar que es ejecutada de manera recursiva.
;;
;; lista: Lista con datos a verificar.
;; seleccion: Lista con las posiciones que deben ser afectadas.
;; f: Funcion lambda entregada.
(define(modsel_simple lista seleccion f)
  (modsel_simple_aux lista seleccion f 0 '())
  )

;; Descripcion de la funcion cantNum
;;
;; Funcion que ejecuta las funciones lambdas entregadas y hace conteo de la cantidad de numeros mayores o menores al umbral.
;;
;; newValue: Lista vacia que registrara los cambios efectuados.
;; lista: Lista con datos a verificar.
;; afectados: Lista con las posiciones que deben ser afectadas.
;; funcion: Funcion lambda entregada para aplicar a los numeros que cumplan la condicion.
;; contador1: contador para la posicion actual en la lista.
;; umbral: Numero que establece el limite.
;; tipo: Caracter que dicta si busca un numero mayor o menor.
(define (cantNum newValue lista afectados funcion contador1 umbral tipo)
  (append newValue (modsel_simple lista afectados funcion))
  (cond
    [(not(null? lista))(if (eq? (verificar afectados contador1) #t)
                           (cantNum (append newValue (list (funcion (car lista)))) (cdr lista) afectados funcion (+ contador1 1) umbral tipo) 
                           (cantNum (append newValue (list (car lista))) (cdr lista) afectados funcion (+ contador1 1) umbral tipo)
                           )]
    [(equal? tipo #\m) (length (umbral_simple newValue umbral #\m))]
    [(equal? tipo #\M) (length (umbral_simple newValue umbral #\M))]
      )
  
  )

;; Descripcion de la funcion estables
;;
;; Funcion que retorna una lista con cantidad de numeros mayore y menores, luego de aplicar 2 funciones lambda a una lista.
;;
;; lista: Lista con datos a verificar.
;; umbral: Numero que establece el limite.
;; fM: Funcion lambda entregada para numeros mayores que el umbral.
;; fm: Funcion lambda entregada para numeros menores que el umbral.
(define (estables lista umbral fM fm)
  (let
      ([posMayores (umbral_simple lista umbral #\M)]
       [posMenores (umbral_simple lista umbral #\m)]
       )
    (cons (cantNum '() lista posMayores fM 0 umbral #\M) (list(cantNum '() lista posMenores fm 0 umbral #\m)))
      )
  )

;; Descripcion de la funcion estables
;;
;; Funcion que obtiene un dato de una lista.
;;
;; lista: Lista con datos a obtener.
;; pos: Posicion del dato a obtener en la lista.
;; contador: Numero que debe coincidir con la posicion deseada.
(define (get lista pos contador) 
  (if (= pos contador)
      (car lista)
      (get (cdr lista) pos (+ contador 1)))
  )

;; Descripcion de la funcion estables
;;
;; Funcion que retorna una lista con cantidad de numeros mayore y menores, luego de aplicar 2 funciones lambda a una lista.
;;
;; lista: Lista de listas de enteros.
;; pos: Numero de posicion de una lista de enteros dentro de esta lista de listas.
;; op: Numero entre 1 y 3 que indica la opcion a ejecutar.
;; params: Lista con parametros necesarios para la operacion deseada.
(define(query lista pos op params)
  (let
      ([ListaObtenida (get lista pos 0)])
    (cond
      [(= op 1) (umbral_simple  ListaObtenida (get params 0 0) (get params 1 0))]
      [(= op 2) (modsel_simple  ListaObtenida (get params 0 0) (eval  (get params 1 0)))]
      [(= op 3) (estables ListaObtenida (get params 0 0) (eval  (get params 1 0)) (eval  (get params 2 0)))]
     )
      )
  )

;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 1 1 '(1 #\M))
;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 0 2 '((0 4) (lambda(x)(+ x 100))))
;(modsel_simple '(15 2 1 3 27 5 10) '(0 4 6) (lambda (x) (modulo x 2)))
;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 2 3 '(5 (lambda (x)(/ x 2)) (lambda (x) (* x 2))))
;(estables '(15 2 1 3 27 5 10) 5 (lambda (x)(/ x 2)) (lambda (x) (* x 2)))