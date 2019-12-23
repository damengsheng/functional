default: all

export parents=basic framewk-benchmark framewk-net framewk-orm framewk-service java-standard stream search-engine message-queue jvm-langs jvm-diagnosis
export modules=framewk-service/framewk-service-grpc-proto \
framewk-service/framewk-service-grpc-facade

all: install_parent install

install_parent:
	for i in $(parents); do \
		cd $$i ; mvn clean install -DskipTests -U -Pshushi; cd -; \
	done

install:
	for i in $(modules); do \
		cd $$i ; mvn clean install -DskipTests -U -Pshushi; cd -; \
	done

apm: install_parent
	for i in $(modules); do \
		cd $$i ; mvn clean install -DskipTests -U -Pshushi -Papm; cd -; \
	done

biz:
	for i in $(biz); do \
		cd $$i ; mvn clean install -DskipTests -U -Pshushi; cd -; \
	done

compile:
	for i in $(modules); do \
		cd $$i ; mvn clean compile -DskipTests -U -Pshushi; cd -; \
	done

