(defproject org.clj-commons/digest (or (System/getenv "PROJECT_VERSION") "1.4.10")
  :description "Digest algorithms (MD5, SHA ...) for Clojure"
  :author "Miki Tebeka <miki.tebeka@gmail.com>"
  :url "https://github.com/clj-commons/clj-digest"
  :deploy-repositories [["clojars" {:url "https://repo.clojars.org"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases true}]]          
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]])
