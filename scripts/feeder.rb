require 'json'

books = JSON.parse(File.open("/Users/jtqiu/Downloads/books/books.json").read)

books.each do |book|
    puts "insert into bookish.books(title, author, published_at, isbn) values('#{book["title"]}', '#{book["author"].join(',')}', '#{book["published_at"]}', '#{book["isbn"].last}');"
end
