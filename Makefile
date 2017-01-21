CLASSES = \
myclient.java \
ServerThread.java \
myserver.java 

#JFLAGS=-g
# 设置你的java编译器
# Set your java compiler here:
JC = javac

.SUFFIXES:.java .class
.java.class:
	$(JC)$(JFLAGS) $*.java

default: classes

classes:$(CLASSES:.java=.class)

clean:
	rm -f *.class
