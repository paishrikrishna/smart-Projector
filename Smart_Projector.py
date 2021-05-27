
#Imports Modules
import socket
import keyboard
import pynput
from pynput.mouse import Button, Controller
import time
import netifaces as ni
from barcode import Code128
from barcode.writer import ImageWriter
from PIL import Image


ni.ifaddresses('wlp2s0')
ip = ni.ifaddresses('wlp2s0')[ni.AF_INET][0]['addr']
with open('somefile.jpeg', 'wb') as f:
    Code128(ip, writer=ImageWriter()).write(f)
image = Image.open('somefile.jpeg')
image.show()
mouse= Controller()
#Defines Server Values
listensocket = socket.socket()
Port = 8000
maxConnections = 999
IP = socket.gethostname() #Gets Hostname Of Current Macheine

listensocket.bind(('',Port))

#Opens Server
listensocket.listen(maxConnections)
print("Server started at " + IP + " on port " + str(Port))
print("Your IP Address IS "+ip)
#Accepts Incomming Connection
(clientsocket, address) = listensocket.accept()
#print("New connection made!")


#Sets Up Indicator LED
#GPIO.setmode(GPIO.BOARD)
#GPIO.setup(7,GPIO.OUT)

#Main

while True:
	
    (clientsocket, address) = listensocket.accept()
    msg=""
    msg = clientsocket.recv(1024).decode() #Receives Message
    if not msg=="":
        print(msg) #Prints Message
    if msg == 'U':
        mouse.move(0,10)
    elif msg=="R":
        mouse.move(-10,0)
    elif msg=="L":
        mouse.move(10,0)
    elif msg=="D":
        mouse.move(0,-10)    
    elif msg=="N":
        mouse.click(Button.left,1)
    elif msg=="B":
        mouse.click(Button.right,1)
    elif msg=="Ll" or msg=="LlLl" or msg=="lL" or msg=="lLlL":
        mouse.move(2,2)
    elif msg=="Rr" or msg=="RrRr" or msg=="rR" or msg=="rRrR":
        mouse.move(-2,2)
    elif msg=="Rd" or msg=="RdRd" or msg=="dR" or msg=="dRdR":
        mouse.move(-2,-2)
    elif msg=="La" or msg=="LaLa" or msg=="aL" or msg=="aLaL":
        mouse.move(2,-2)
    elif msg=="A":
        webbrowser.open("https://www.youtube.com/")
    elif msg=="b":
        webbrowser.open("https://www.google.co.in/")
    elif msg=="C":
        webbrowser.open("http://mu.ac.in/portal/")
    elif msg=="G":
        webbrowser.open("https://www.google.com/drive/")
    elif msg=="*":
        mouse.scroll(0,-1)
    elif msg=="#":
        mouse.scroll(0,1)
    