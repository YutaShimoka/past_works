\encoding UTF8;
\copy product_table from program 'gzip -dc ../../../result/test_data.tsv.gz' ( format csv, delimiter E'\t', header true );
reset client_encoding;
