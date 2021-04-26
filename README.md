# wxpay-sdk

## 一、导入依赖

#### 1、添加Github Packages仓库

```xml

<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/snippet0809/wxpay-sdk</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

注意，若在~/.m2/setting.xml中配置了&lt;mirrorOf>*<mirrorOf&gt;的镜像，会被误伤的

#### 2、导入jar包

```xml

<dependency>
    <groupId>io.github.snippet0809</groupId>
    <artifactId>wxpay-sdk</artifactId>
    <version>${wxpay-sdk.version}</version>
</dependency>
```

#### 3、配置Github Token

当前即使从Github Packages下载public包，依然需要token（Github官方说以后可能会更新，
详见https://github.community/t/npm-install-from-github-leads-to-401-gpr-npm-registry-for-public-packages-really-needs-realm-token/2877），
故需要在maven的setting.xml（默认路径为~/.m2/setting.xml或IntelliJ IDEA右键项目 -> maven -> Open 'setting.xml'）中添加github认证配置

```xml

<servers>
    <server>
        <id>github</id>
        <username>USERNAME</username>
        <password>TOKEN</password>
    </server>
</servers>

```
