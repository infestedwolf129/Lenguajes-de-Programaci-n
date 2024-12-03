import numpy as np # pip install numpy
from PIL import Image # pip install Pllow
import re
import os


def MatrizAImagen(matriz, filename='pixelart.png', factor=10):
    '''
    Convierte una matriz de valores RGB en una imagen y la guarda como un archivo png.
    Las imagenes son escaladas por un factor ya que con los ejemplos se producirian imagenes muy pequeñas.
        Parametros:
                matriz (lista de lista de tuplas de enteros): Matriz que representa la imagen en rgb.
                filename (str): Nombre del archivo en que se guardara la imagen.
                factor (int): Factor por el cual se escala el tamaño de las imagenes.
    '''
    matriz = np.array(matriz, dtype=np.uint8)
    np.swapaxes(matriz, 0, -1)

    N = np.shape(matriz)[0]

    img = Image.fromarray(matriz, 'RGB')
    img = img.resize((N*factor, N*factor), Image.Resampling.BOX)
    img.save(filename)


def generarMatriz(Ancho,colorFondo):
    '''
    A partir de 2 parametros la funcion se encarga de generar una matriz de tamaño (n x n).
    En el parametro colorFondo existe la posibilidad de que sea un parametro tipo str o lista, dependiendo si es un color personalizado RGB.

        Parametros:
                Ancho (int): Valor entero que determina el tamaño de la matriz.
                colorFondo (str | lista con datos RGB): Llave en formato de string para acceder a una tupla especifica con el color pedido. | Lista que contiene los valores RGB a tomar.

        Retorno:
                matriz (lista de lista de tuplas): Retorna una matriz en la forma de lista de listas de tuplas.
    '''
    global colores
    matriz = []
    for i in range(Ancho): 
        matriz.append([(0,0,0)]*Ancho)
    for i in range(Ancho):
        for j in range(Ancho):
            if 'RGB' in colorFondo:
                matriz[i][j] = colorFondo[1]
            else:
                matriz[i][j] = colores[colorFondo]

    return matriz
        

def Derecha():
    '''
    Funcion que usa una variable global llamada orientacion, afectando su valor para indicar un sentido de vista sobre la matriz.
        Parametros:
                No recibe parametros.
        
        Retorno:
                orientacion (int): Retorna la orientacion con el valor del punto de vista pedido.
    '''
    global orientacion
    if orientacion == 0:
        orientacion = 3
    else:
        orientacion -= 1
    return orientacion


def Izquierda():
    '''
    Funcion que usa una variable global llamada orientacion, afectando su valor para indicar un sentido de vista sobre la matriz.
        Parametros:
                No recibe parametros.
        
        Retorno:
                orientacion (int): Retorna la orientacion con el valor del punto de vista pedido.
    '''
    global orientacion
    if orientacion == 3:
        orientacion = 0
    else:
        orientacion += 1

    return orientacion

def AvanzarN(N,elemento):
    '''
    Toma en consideracion variables globales como lo es la posicion actual en fila y columna, ademas de la orientacion en la que se esta observando la matriz.
    Dependiendo de la orientacion, se decide si se avanza en posicion columna o posicion fila, donde la orientacion corresponde a:
        Derecha = 0, Arriba = 1, Izquierda = 2, Abajo = 3. 
        Parametros:
                N (int): Numero entero que indica la cantidad de espacios a moverse dentro de la matriz.

    '''
    global orientacion,posColumna,posFila,Ancho,NumeroLinea
    if orientacion == 0:
        if posColumna != Ancho and (posColumna + N) < Ancho :
            posColumna += N
        else:
            print(f'Salio de la matriz en la linea: {NumeroLinea} {elemento}')
            quit()
    elif orientacion == 2:
        if posColumna != 0 and (posColumna - N) >= 0 :
            posColumna -= N
        else:
            print(f'Salio de la matriz en la linea: {NumeroLinea} {elemento}')
            quit()

    elif orientacion == 1:
        if posFila != 0 and (posFila - N) >= 0 :
            posFila -= N
        else:
            print(f'Salio de la matriz en la linea: {NumeroLinea} {elemento}')
            quit()
    elif orientacion == 3:
        if posFila != Ancho and (posFila + N) < Ancho :
            posFila += N
        else:
            print(f'Salio de la matriz en la linea: {NumeroLinea} {elemento}')
            quit()

def Pintar(color):
    '''
    Dentro de la matriz en la posicion actual reemplaza la tupla por la tupla correspondiente al color pedido.
    En este caso el parametro color tambien puede ser una llave tipo str de un diccionario o bien una lista con un color RGB personalizado
        Parametros:
                color (str | lista): Llave tipo str que accede a un diccionario global con una cantidad de colores especifica. | Lista que contiene la tupla del color RGB pedido.
        
        Retorno:
                matriz: Retorna la matriz modificada con el nuevo color(tupla RGB) en la posicion pedida.
    ''' 
    global matriz,colores,posFila,posColumna
    if 'RGB' in color:
        matriz[posFila][posColumna] = color[1]
    else:
        matriz[posFila][posColumna] = colores[color]

    return matriz


def Repetir(instrucciones,repeticiones):
    '''
    Ejecuta una secuencia de instrucciones una cantidad de veces definida
        Parametros:
               instrucciones (lista con instrucciones): Lista que viene con todas las instrucciones que deben repetirse.
               repeticiones (int): Numero entero con la cantidad de veces a repetir la secuencia de instrucciones.
    ''' 
    flag = False
    i = 1
    while i <= repeticiones:
        j = 0
        print(f'REPETICION NUMERO {i}\n')
        while j < len(instrucciones):
            ejecucion = instrucciones[j]
            #Instruccion Avanzar
            if re.match(r'Avanzar\b',ejecucion):
                flag = True
                if re.search(r'[0-9]\b',instrucciones[j+1]):
                    AvanzarN(int(instrucciones[j+1],elemento))
                else:
                    AvanzarN(1,elemento)
            
            #Instruccion Derecha
            elif re.match(r'Derecha\b', instrucciones[j]):
                flag = True
            
                if re.match(r'\d',instrucciones[j+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    j = len(instrucciones)
                
                else:
                    Derecha()
            
            #Instruccion Izquierda
            elif re.match(r'Izquierda\b',instrucciones[j]):
                flag = True
            
                if re.match(r'\d',instrucciones[j+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    j = len(instrucciones)
                
                else:
                    Izquierda()
            

            #Instruccion Pintar
            elif re.match(r'Pintar\b',instrucciones[j]):
                flag = True
            
                if instrucciones[j+1] in colores:
                    Pintar(instrucciones[j+1])
                
                elif re.match(r'RGB\b',instrucciones[j+1]):
                    encontrado = re.split(r'\b',elemento)
                    lista = []
                    for dato in encontrado:
                        if re.match(r'\d',dato):
                            lista.append(int(dato))
                    color=['RGB',(lista[0],lista[1],lista[2])]
                    Pintar(color)
                
                elif re.match(r'\d',instrucciones[j+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    j = len(instrucciones)

            #Instruccion Repetir
            elif re.match('Repetir',instrucciones[j]):
                flag = True

                if re.match(r'\d',instrucciones[j+1]):
                    repeticiones1 = int(instrucciones[j+1])
                
                if not re.match(r'\d',instrucciones[j+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    j = len(instrucciones)
                

                if re.match('veces',instrucciones[j+2]):
                    x = j+3
                    instrucciones1 = []
                    while x < len(instrucciones):  
                        if instrucciones[x] != '{':
                            if instrucciones[x] == '}':
                                x=len(instrucciones)
                            else:
                                instrucciones1.append(instrucciones[x]) 
                        x+=1
                    Repetir(instrucciones1,repeticiones1)

       
            
            
            #Agregar error en caso de no coincidir con ningun comando anterior
            elif flag == False and busqueda[i] != '':
                errores.write(f'{NumeroLinea} {elemento}')
                i = len(busqueda)
            i += 1
            j += 1
        
        i += 1


#Variables globales
colores= {"Rojo":(255,0,0),'Verde':(0,255,0),'Azul':(0,0,255),'Negro':(0,0,0),'Blanco':(255,255,255)}
orientacion = 0
posFila = 0
posColumna = 0


#Operacion sobre archivos
archivo = open("codigo.txt")
errores = open('errores.txt','w')
lineas = archivo.readlines()

#Obtencion de parametros de matriz
for elemento in lineas:
    if re.findall('^Ancho',elemento):
        Ancho = int(elemento[5:])
    elif re.findall('^Color de fondo',elemento):
        if re.search('RGB',elemento):
                encontrado = re.split(r'\b',elemento)
                lista = []
                for dato in encontrado:
                    if re.search(r'\d',dato):
                        lista.append(int(dato))
                colorFondo=['RGB',(lista[0],lista[1],lista[2])]
                
                
        else:
            colorFondo = elemento[14:]
            colorFondo = colorFondo.strip()


#Creacion de matriz
if 'RGB' in colorFondo:
    matriz = generarMatriz(Ancho,colorFondo)
else:
    matriz = generarMatriz(Ancho,colorFondo)





#Lectura de instrucciones
NumeroLinea = 1
for elemento in lineas:
    busqueda = re.split(r'\W+',elemento)
    i = 0
    flag = False
    if not re.findall('Ancho',elemento) and not re.findall('^Color de fondo',elemento):
        while i < len(busqueda):

            #Instruccion Avanzar
            if re.match(r'Avanzar\b',busqueda[i]):
                flag = True

                if re.search(r'[0-9]\b',busqueda[i+1]):
                    AvanzarN(int(busqueda[i+1]),elemento)

                else:
                    AvanzarN(1,elemento)
            
            #Instruccion Derecha
            elif re.match(r'Derecha\b', busqueda[i]):
                flag = True
                if re.match(r'\d',busqueda[i+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    i = len(busqueda)

                else:
                    Derecha()
            
            #Instruccion Izquierda
            elif re.match(r'Izquierda\b',busqueda[i]):
                flag = True
                if re.match(r'\d',busqueda[i+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    i = len(busqueda)

                else:
                    Izquierda()
            

            #Instruccion Pintar
            elif re.match(r'Pintar\b',busqueda[i]):
                flag = True
                if busqueda[i+1] in colores:
                    Pintar(busqueda[i+1])

                elif re.match(r'RGB\b',busqueda[i+1]):
                    encontrado = re.split(r'\b',elemento)
                    lista = []
                    for dato in encontrado:
                        if re.match(r'\d',dato):
                            lista.append(int(dato))
                    color=['RGB',(lista[0],lista[1],lista[2])]
                    Pintar(color)

                elif re.match(r'\d',busqueda[i+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    i = len(busqueda)

            #Instruccion Repetir
            elif re.match('Repetir',busqueda[i]):
                if re.match(r'\d',busqueda[i+1]):
                    repeticiones = int(busqueda[i+1])

                if not re.match(r'\d',busqueda[i+1]):
                    errores.write(f'{NumeroLinea} {elemento}')
                    i = len(instrucciones)

                if re.match('veces',busqueda[i+2]):
                    j = i+3
                    instrucciones = []
                    while j < len(busqueda):  
                        if busqueda[j] != '{':
                            if busqueda[j] == '}':
                                j=len(busqueda)
                            else:
                                instrucciones.append(busqueda[j]) 
                        j+=1
                    Repetir(instrucciones,repeticiones)
                
            #Agregar error en caso de no coincidir con ningun comando anterior
            elif flag == False and busqueda[i] != '':
                errores.write(f'{NumeroLinea} {elemento}')
                i = len(busqueda)
            i += 1
    NumeroLinea += 1

#Creacion pixelart.png
MatrizAImagen(matriz)

#Cierre de archivos
errores.close()
archivo.close()

#Modificacion archivo errores.txt en caso de no existir error alguno en las instrucciones
if os.stat('errores.txt').st_size == 0:
    archivo = open('errores.txt','w')
    archivo.write('No hay errores!')
    archivo.close()








