CREATE TABLE data_autodebet
(
    noka VARCHAR(13),
    haridebet INT,
    norekening VARCHAR(100),
    namaonrekening VARCHAR(100),
    nohp VARCHAR(100),
    alamatemail VARCHAR(100),
    status_ INT
);

INSERT INTO data_autodebet
    (noka, haridebet, norekening, namaonrekening, nohp, alamatemail, status_)
VALUES
    ('0001265125534', 1, '1111112222', 'Rizki Saja', '0888888888898', 'rizki.saja@gmail.com', 0),
    ('0001265125535', 1, '3334443342', 'Fani Aja', '0888882234898', 'fani.aja@gmail.com', 1);