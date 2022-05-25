-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'insulin_tracker';

DROP DATABASE insulin_tracker;

DROP USER insulin_tracker_owner;
DROP USER insulin_tracker_appuser;
