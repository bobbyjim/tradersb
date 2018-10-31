# Dockerfile for Trader-SpringBoot

# Get a Linux with Java and Bash
FROM anapsix/alpine-java

# Trader listens on port 10203
EXPOSE 10203

# Copy JARfile
COPY out/artifacts/tradersb_jar/tradersb.jar .

# Run it
CMD java -cp tradersb.jar net.eagle.tas.tradersb.TraderSBApplication
