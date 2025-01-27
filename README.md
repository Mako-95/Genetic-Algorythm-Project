# Genetic Algorythm Project

Program umieszcza obiekty 2D na ograniczonej siatce kwadratowej o określonych wymiarach.
Stosuje do tego strategię ewolucyjną 1+1, gdzie osobnikiem jest zbiór położeń (koordynaty x i y) obiektów z listy.

Klasa Item reprezentuje pojedyńczy obiekt, który posiada swoje położenie, kształt, kolor i flagę informującą czy dany obiekt został umieszczony.
Klasa Unit reprezentuje pojedyńczego osobnika, który posiada listę obiektów do umieszczenia, kształt układu z umieszczonymi obiektami i miarę dopasowania.
Zawiera również definicję operatora mutacji.
Klasy SuitCasePane oraz ItemPane służą wyłącznie do wizualizacji odpowiednio układu "walizki" i listy obiektów.
Klasa CenterPane koordynuje ewolucję układu, generując kolejne osobniki i ich mutując, oraz przekazuje dane z klas osobników do klas wizualizacji.
Zawiera parametry układu, na podstawie których generowane są osobniki klasy Unit i obiekty klasy Item.
Klasa Main wywołuje program i zawiera interfejs użytkownika.
