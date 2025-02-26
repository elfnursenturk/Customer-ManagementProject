## Customer Management System

Bu proje, müşteri yönetimi için geliştirilmiş bir sistemdir. Kullanıcılar sisteme kayıt olabilir, giriş yapabilir ve müşteri bilgilerini ekleyip yönetebilir.

## Proje Özellikleri

Kullanıcı Kaydı ve Girişi: Kullanıcılar sisteme kayıt olabilir ve giriş yapabilir.

Müşteri Yönetimi: Müşteri ekleme, listeleme, güncelleme ve silme işlemleri yapılabilir.

Validasyonlar: Kullanıcı girişleri doğrulama kurallarına uygun olmalıdır.

Filtreleme ve Sayfalama: Müşteri listesi filtrelenebilir ve sayfalanabilir.

Veritabanı Bağlantısı: PostgreSQL kullanılarak müşteri bilgileri saklanmaktadır.

Kullanılan Teknolojiler

## Backend: Java, Spring Boot, Spring Security, PostgreSQL

## Frontend: React, Bootstrap

Build Araçları: Maven

Kurulum Adımları

## Backend (Spring Boot) Kurulumu

Projeyi klonlayın:

git clone https://github.com/elfnursenturk/customer-management

Proje dizinine girin:

cd customer-management

Gerekli bağımlılıkları yükleyin ve çalıştırın:

mvn spring-boot:run

## Frontend (React) Kurulumu

client klasörüne gidin:

cd customer-management

## Bağımlılıkları yükleyin:

npm install

## Uygulamayı çalıştırın:

npm run dev

API Endpointleri

HTTP Metodu

URL

Açıklama

## POST

/rest/api/user/save

Yeni kullanıcı oluşturur

## GET

/rest/api/user/list

Tüm müşterileri listeler

## PUT

/rest/api/user/update/{id}

Müşteri bilgilerini günceller

## DELETE

/rest/api/user/delete/{id}

Belirtilen müşteriyi siler

## Notlar

React frontend kısmında Bootstrap kullanılmıştır.

Şu an için JWT kimlik doğrulaması uygulanmamıştır, ancak eklenebilir.

Tüm giriş ve kayıt işlemlerinde validasyonlar bulunmaktadır.