JAVA		= java
JAVAC		= javac --release 22 -encoding UTF8
ANTLR		= org.antlr.v4.Tool
ANTLRDIR	= lib/antlr-4.13.2-complete.jar
RM			= 'rm' -fr
FIND		= 'find'
ENROLLEMENT_NUMBER = 63220017

all	:
	if [ -d src/compiler/phase/lexan ] ; then $(MAKE) -C src/compiler/phase/lexan ; fi
	if [ -d src/compiler/phase/synan ] ; then $(MAKE) -C src/compiler/phase/synan ; fi
	$(JAVAC) --module-path $(ANTLRDIR) --source-path src -d bin src/compiler/Compiler.java
	# $(MAKE) -C doc
	@echo ":-) OK"

.PHONY	: clean
clean	:
	if [ -d doc ] ; then $(MAKE) -C doc clean ; fi
	if [ -d src ] ; then $(MAKE) -C prg clean ; fi
	if [ -d src/compiler/phase/lexan ] ; then $(MAKE) -C src/compiler/phase/lexan clean ; fi
	if [ -d src/compiler/phase/synan ] ; then $(MAKE) -C src/compiler/phase/synan clean ; fi
	$(FIND) . -type f -iname "*~" -exec $(RM) {} \;
	$(FIND) . -type f -iname "*.class" -exec $(RM) {} \;
	$(RM) bin

zip: clean
	@if [ -z "$(NAME)" ]; then \
		echo "NAME not set! Please set it with \`make zip NAME=<name>\`"; \
		exit 1; \
	fi
	@current_dir=`basename "$$(pwd)"`; \
	if [ $$current_dir == "prev25" ]; then \
		cd .. && zip -r "$(ENROLLMENT_NUMBER).zip" $$current_dir -x "$$current_dir/lib/antlr-4.13.2-complete.jar" && mv "$(ENROLLMENT_NUMBER).zip" "$$current_dir/$(ENROLLMENT_NUMBER)-$(NAME).zip"; \
	else \
		echo ; \
		echo "ERROR: Your directory isn't named 'prev25'! Aborting..."; \
		echo "it is $$current_dir"; \
	  	exit 1; \
	fi
