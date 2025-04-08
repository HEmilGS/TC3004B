import random
import time
from enum import Enum

class Evento(Enum):
    DISPARO_DESVIADO = 1
    PORTERO_ATAJA = 2
    GOL_CABEZA = 3
    GOL_PENAL = 4
    GOL_PIE = 5

class Equipo:
    def __init__(self, nombre):
        self.nombre = nombre
        self.goles = 0
    
    def anotar_gol(self):
        self.goles += 1
        return self.goles

class SimuladorPartido:
    def __init__(self):
        self.chivas = Equipo("Chivas")
        self.atlas = Equipo("Atlas")
        self.equipo_turno = None
        self.minuto = 0
    
    def determinar_evento(self, numero):
        if 1 <= numero <= 100:
            return Evento.DISPARO_DESVIADO
        elif 101 <= numero <= 200:
            return Evento.PORTERO_ATAJA
        elif 201 <= numero <= 250:
            return Evento.GOL_CABEZA
        elif 251 <= numero <= 300:
            return Evento.GOL_PENAL
        elif 301 <= numero <= 350:
            return Evento.GOL_PIE
        else:
            return None
    
    def cambiar_turno(self):
        self.equipo_turno = self.atlas if self.equipo_turno == self.chivas else self.chivas
    
    def simular_minuto(self):
        self.minuto += 1
        self.cambiar_turno()
        
        numero_aleatorio = random.randint(1, 350)
        evento = self.determinar_evento(numero_aleatorio)
        
        if evento == Evento.DISPARO_DESVIADO:
            print(f"Min {self.minuto}: {self.equipo_turno.nombre} - Disparo desviado")
        elif evento == Evento.PORTERO_ATAJA:
            print(f"Min {self.minuto}: {self.equipo_turno.nombre} - El portero ataja el balón")
        elif evento == Evento.GOL_CABEZA:
            goles = self.equipo_turno.anotar_gol()
            print(f"Min {self.minuto}: GOOOOL!!! {self.equipo_turno.nombre} anota de cabeza ({goles}-{self.get_goles_contrario()})")
        elif evento == Evento.GOL_PENAL:
            goles = self.equipo_turno.anotar_gol()
            print(f"Min {self.minuto}: GOOOOL!!! {self.equipo_turno.nombre} anota de penal ({goles}-{self.get_goles_contrario()})")
        elif evento == Evento.GOL_PIE:
            goles = self.equipo_turno.anotar_gol()
            print(f"Min {self.minuto}: GOOOOL!!! {self.equipo_turno.nombre} anota con el pie ({goles}-{self.get_goles_contrario()})")
    
    def get_goles_contrario(self):
        return self.atlas.goles if self.equipo_turno == self.chivas else self.chivas.goles
    
    def simular_partido(self):
        print("¡Comienza el partido entre Chivas y Atlas!")
        # El sorteo inicial determina qué equipo empieza con el balón
        self.equipo_turno = random.choice([self.chivas, self.atlas])
        print(f"{self.equipo_turno.nombre} comienza con el balón")
        
        while self.minuto < 90:
            self.simular_minuto()
            time.sleep(0.1)  # Pequeña pausa para que se pueda leer la simulación
        
        print("\n¡Final del partido!")
        print(f"Resultado final: Chivas {self.chivas.goles} - {self.atlas.goles} Atlas")
        
        if self.chivas.goles > self.atlas.goles:
            print("¡Chivas gana el partido!")
        elif self.atlas.goles > self.chivas.goles:
            print("¡Atlas gana el partido!")
        else:
            print("¡El partido termina en empate!")

if __name__ == "__main__":
    simulador = SimuladorPartido()
    simulador.simular_partido()
