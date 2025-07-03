SELECT * FROM quanlythuvien.penalties;
ALTER TABLE penalties
ADD CONSTRAINT fk_penalties_borrow_detail
FOREIGN KEY (borrow_detail_id) REFERENCES borrow_details(borrow_detail_id)
ON DELETE CASCADE;