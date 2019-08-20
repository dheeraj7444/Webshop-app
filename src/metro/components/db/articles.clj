(ns metro.components.db.articles
  (:require [korma.core :as kc]))

(kc/defentity article
  (kc/table :articles)
  (kc/entity-fields :id :title :description :image :count))

(defn drop-table! []
  (kc/exec-raw "DROP TABLE IF EXISTS articles"))

(defn clear-table! []
  (kc/delete article))

(defn add! [title description image count]
  (kc/insert article (kc/values {:title title
                                 :description description
                                 :image image
                                 :count count})))

(defn delete! [id]
  (kc/delete article (kc/where {:id id})))

(defn inc! [id]
  (kc/exec-raw (format "UPDATE articles SET count = count + 1 WHERE id = %s" id)))

(defn dec! [id]
  (kc/exec-raw (format "UPDATE articles SET count = count - 1 WHERE id = %s" id)))

(defn query-all []
  (kc/select article))

(defn create-table! []
  (drop-table!)
  (kc/exec-raw "CREATE TABLE IF NOT EXISTS articles(id SERIAL, title TEXT NOT NULL, description TEXT NOT NULL, image TEXT NOT NULL, count INTEGER DEFAULT 0 NOT NULL)")
  (add! "Apples"
        "An apple is a sweet, edible fruit produced by an apple tree."
        "img/apples.jpeg"
        0)
  (add! "Apricot"
        "An apricot is a fruit, or the tree that bears the fruit, of several species in the genus Prunus (stone fruits)."
        "img/apricotes.jpeg"
        0)
  (add! "Banana"
        "A banana is an edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called \"plantains\", distinguishing them from dessert bananas."
        "img/banana.jpeg"
        0)
  (add! "Beef"
        "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times. Beef is a source of high-quality protein and nutrients."
        "img/beef.jpeg"
        0)
  (add! "Ben & Jerrys"
        "Ben & Jerry's Homemade Holdings Inc, trading and commonly known as Ben & Jerry's, is an American company that manufactures ice cream, frozen yogurt, and sorbet."
        "img/benjerrys.jpeg"
        0)
  (add! "Fish"
        "Many species of fish are consumed as food in virtually all regions around the world. Fish has been an important source of protein and other nutrients for humans throughout history. "
        "img/fish.jpeg"
        0)
  (add! "Pepper"
        "Black pepper (Piper nigrum) is a flowering vine in the family Piperaceae, cultivated for its fruit, known as a peppercorn, which is usually dried and used as a spice and seasoning."
        "img/pepper.jpeg"
        0)
  (add! "Rum"
        "Rum is a distilled alcoholic drink made from sugarcane byproducts, such as molasses, or directly from sugarcane juice, by a process of fermentation and distillation. The distillate, a clear liquid, is then usually aged in oak barrels. "
        "img/rum.jpeg"
        0)
  (add! "Salt"
        "Salt is a mineral composed primarily of sodium chloride (NaCl), a chemical compound belonging to the larger class of salts; salt in its natural form as a crystalline mineral is known as rock salt or halite. "
        "img/salt.jpeg"
        0)
  (add! "Water"
        "Drinking water, also known as potable water, is water that is safe to drink or to use for food preparation. The amount of drinking water required to maintain good health varies, and depends on physical activity level, age, health-related issues, and environmental conditions."
        "img/water.jpeg"
        0))

(comment
  (create-table!)
  (add! "deleteme" "deleteit" 0)
  (delete! 10)
  (clear-table!))
