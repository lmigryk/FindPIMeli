default:
	cat ./makefile.mk
compile:
	./mvnw clean install
docker-run:
	docker compose up -d --build
run-project:
	compile docker-run