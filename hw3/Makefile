JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
    Device.java \
    ElectronicDevice.java \
    Headphones.java \
    Inventory.java \
    Laptop.java \
    Menu.java \
    Smartphone.java \
    Smartwatch.java \
    TV.java

all : Menu.class

run : 
    $(JR) Menu

classes : $(CLASSES:.java=.class)

%.class : %.java
    $(JC) $(JFLAGS) $<

doc:
    $(JD) $(JDFLAGS) *.java 

clean:
    $(RM) *.class 

cleandoc:
    $(RM) -r ./javadoc
