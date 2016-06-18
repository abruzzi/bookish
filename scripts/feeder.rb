require 'json'

books = JSON.parse(File.open("scripts/books.json").read)

puts "truncate books;"
books.each do |book|
    puts "insert into bookish.books(title, author, published_at, isbn, asin, description) values('#{book["title"]}', '#{book["author"].join(',')}', '#{book["published_at"]}', '#{book["isbn"].last}', '#{book["asin"]}', '#{book["title"] * 10}');"
end
