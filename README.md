# Easy-Commands
Minecraft command framework for Spigot or Bukkit 1.15!

## Installing

As there's no maven repository you must install the framework manually.

1. Clone the repository.

```
git clone https://github.com/xChevy/Easy-Commands.git
```

2. Enter the repository directory.

```
cd Easy-Commands
```

3. Run maven to build and copy the framework to your ./m2

```
mvn clean install
```

4. Add the dependency to your project pom.xml.


### Bukkit

```xml
<dependency>
  <artifactId>bukkit</artifactId>
  <groupId>com.starfishst.bukkit</groupId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### Bungee

```xml
<dependency>
  <artifactId>bungee</artifactId>
  <groupId>com.starfishst.bungee</groupId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### JDA 

<dependency>
  <artifactId>JDA-Command-Framework</artifactId>
  <groupId>com.starfishst.commands</groupId>
  <version>1.0-SNAPSHOT</version>
</dependency>

5. Read the documentation and create your own commands!

# TO-DO
